package com.xx.service;

import com.xx.dao.RegisterDao;
import com.xx.vo.User;
import com.xx.vo.UserInfo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class RegisterService
{
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private BCryptPasswordEncoder encoder;
    List a;
    //注册
    public boolean registerUser(User user,String code)
    {
        if (code.equals((String)redisTemplate.opsForValue().get("SmsCode"+user.getPhone())))
        {
            int userId = getId();
            user.setUserId(userId);
            user.setAdmin("0");
            String endcoderPassword = encoder.encode(user.getPassword());
            user.setPassword(endcoderPassword);
            registerDao.registerUser(user);
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setPhone(user.getPhone());
            userInfo.setRegisterDate(getTime());
            registerDao.registerUserInfo(userInfo);
            return true;
        }else{
            return false;
        }
    }

    //Id+1
    public int getId()
    {
        long a;
        long b;
        redisTemplate.delete("userId");
        boolean flag = redisTemplate.hasKey("userId");
        if (flag)
        {
            System.out.println("存在");
            a = redisTemplate.opsForList().size("userId");
            b = a+1;
            redisTemplate.opsForList().set("userId",a,b);
        }else{
            System.out.println("不存在");
            a = 0;
            b = 1;
            redisTemplate.opsForList().set("userId",a,b);
            System.out.println("hello");
        }
        List c = redisTemplate.opsForList().range("userId",a,a+1);
        System.out.println(c.size());
        int d = c.indexOf(0);
        System.out.println(d);
        return d;
    }

    //发送验证码
    public void sendCode(String phone,String code)
    {
        Map<String,String> map = new HashMap();
        map.put("phone",phone);
        map.put("smsCode",code);
        System.out.println(phone+":"+code);
        rabbitTemplate.convertAndSend("sms",map);
    }

    //取验证码
    public String getSmsCode(String phone)
    {
        String code = null;
        boolean isExist = redisTemplate.hasKey("SmsCode"+phone);
        if (isExist){
            code = (String)redisTemplate.opsForValue().get("SmsCode"+phone);
            return code;
        }else{
            code = createCode();
            redisTemplate.opsForValue().set("SmsCode"+phone,code,60, TimeUnit.SECONDS);
            return (String)redisTemplate.opsForValue().get("SmsCode"+phone);
        }
    }

    //生成验证码
    public static String createCode()
    {
        String code = "";
        for (int i = 0 ; i < 6 ; i ++)
        {
            code = code + String.valueOf((int) Math.floor(Math.random()*9+1));
        }
            return code;
    }

    //判断账号是否已经被注册
    public boolean isExistAccount(String account)
    {
        boolean flag = registerDao.isExistAccount(account);
        return flag;
    }

    //判断手机号是否已经被注册
    public boolean isExistPhone(String phone)
    {
        boolean flag = registerDao.isExistPhone(phone);
        return flag;
    }

    //获取当前时间
    public String getTime()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return dateFormat.format(date);
    }
}
