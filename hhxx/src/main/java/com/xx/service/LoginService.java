package com.xx.service;

import com.xx.dao.LoginDao;
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
}
