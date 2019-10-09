package com.xx.service;

import com.xx.dao.RegisterDao;
import com.xx.vo.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

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

    //注册
    public void registerUser(User user)
    {
        String userId = getId();
        user.setUserId(userId);
        user.setAdmin("0");
        registerDao.registerUser(user);
    }
    //Id自增
    public String getId()
    {
        boolean flag = redisTemplate.hasKey("userId");
        if (flag)
        {
            String a = (String) redisTemplate.opsForValue().get("userId");
            int b = Integer.parseInt(a)+1;
            redisTemplate.opsForValue().set("userId",b);
        }else{
            redisTemplate.opsForValue().set("userId",1);
        }
        String userId = (String)redisTemplate.opsForValue().get("userId");
        System.out.println(userId);
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
}
