package com.xx.service;

import com.xx.dao.RegisterDao;
import com.xx.vo.User;
import com.xx.vo.UserInfo;
import com.xx.vo.UserSum;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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
    //注册
    public boolean registerUser(User user,String code)
    {
        if (code.equals(redisTemplate.opsForValue().get("SmsCode"+user.getPhone())))
        {
            String userId = getId();
            user.setUserId(userId);
            user.setAdmin("0");
            String endcoderPassword = encoder.encode(user.getPassword());
            user.setPassword(endcoderPassword);
            registerDao.registerUser(user);
            //userInfo表
            UserInfo userInfo = new UserInfo();
            userInfo.setUserId(userId);
            userInfo.setUserName(user.getUserName());
            userInfo.setPhone(user.getPhone());
            userInfo.setRegisterDate(getTime());
            registerDao.registerUserInfo(userInfo);
            //userSum表
            UserSum userSum = new UserSum();
            userSum.setUserId(userId);
            userSum.setBlogs(0);
            userSum.setFabulous(0);
            userSum.setFans(0);
            userSum.setVisit(0);
            registerDao.registerUserSum(userSum);

            redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"userName",userInfo.getUserName());
            redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"phone",userInfo.getPhone());
            redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"registerDate",userInfo.getRegisterDate());
            return true;
        }else{
            return false;
        }
    }

    //获取userId
    public String getId()
    {
        boolean flag = redisTemplate.hasKey("userId");
        if (flag)
        {
            String a =redisTemplate.opsForValue().get("userId").toString();
            int b = Integer.parseInt(a)+1;
            redisTemplate.opsForValue().set("userId",b);
        }else{
            redisTemplate.opsForValue().set("userId",1);
        }
        String userId =redisTemplate.opsForValue().get("userId").toString();
        return userId;
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
