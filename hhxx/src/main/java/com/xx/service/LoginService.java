package com.xx.service;

import com.xx.dao.LoginDao;
import com.xx.dao.RegisterDao;
import com.xx.vo.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService
{
    @Autowired
    private LoginDao loginDao;
    @Autowired
    private RegisterDao registerDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BCryptPasswordEncoder encoder;

    //账号密码登陆，验证账号密码
    public boolean accountLogin(Login login)
    {
        String password = loginDao.findPassword(login.getAccount());
        boolean flag = encoder.matches(login.getPassword(),password);
        return flag;
    }

    //手机登陆，验证验证码
    public boolean phoneLogin(String phone,String code)
    {
        boolean flag;
        flag = code.equals(redisTemplate.opsForValue().get("SmsCode"+phone));
        return flag;
    }

    //判断手机号是否已经被注册
    public boolean isExistPhone(String phone)
    {
        boolean flag = registerDao.isExistPhone(phone);
        return flag;
    }

    //获取Id
    public String getUserId(String account)
    {
        String userId = loginDao.findUserId(account);
        return userId;
    }

}
