package com.xx.controller;

import com.xx.service.LoginService;
import com.xx.service.RegisterService;
import com.xx.vo.Login;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("pages")
public class LoginController
{
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 账号密码登陆
     * @param login
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Login login, HttpSession httpSession)
    {
        System.out.println("login登陆");
        boolean flag = loginService.accountLogin(login);
        if (flag)
        {
            String userId = loginService.getAccountUserId(login.getAccount());
            httpSession.setAttribute("userId",userId);
            httpSession.setAttribute("userName",redisTemplate.opsForHash().entries("user_"+userId).get("userName"));
            httpSession.setAttribute("email",redisTemplate.opsForHash().entries("user_"+userId).get("email"));
            httpSession.setAttribute("phone",redisTemplate.opsForHash().entries("user_"+userId).get("phone"));
            httpSession.setAttribute("sex",redisTemplate.opsForHash().entries("user_"+userId).get("sex"));
            httpSession.setAttribute("trade",redisTemplate.opsForHash().entries("user_"+userId).get("trade"));
            httpSession.setAttribute("introduction",redisTemplate.opsForHash().entries("user_"+userId).get("introduction"));
            httpSession.setAttribute("registerDate",redisTemplate.opsForHash().entries("user_"+userId).get("registerDate"));
            return new Result(true,StatusCode.OK,"登陆成功");
        }else{
            return new Result(false,StatusCode.AccountError,"账号或密码错误");
        }
    }

    /**
     * 手机号登陆
     * @param phone
     * @return
     */
    @PostMapping("phoneLogin/{phone}/{code}")
    public Result phoneLogin(@PathVariable String phone,@PathVariable String code,HttpSession httpSession)
    {
        System.out.println("phoneLogin手机号登陆");
        boolean flag = loginService.phoneLogin(phone,code);
        if (flag)
        {
            String userId = loginService.getPhoneUserId(phone);
            httpSession.setAttribute("userId",userId);
            httpSession.setAttribute("userName",redisTemplate.opsForHash().entries("user_"+userId).get("userName"));
            httpSession.setAttribute("email",redisTemplate.opsForHash().entries("user_"+userId).get("email"));
            httpSession.setAttribute("phone",redisTemplate.opsForHash().entries("user_"+userId).get("phone"));
            httpSession.setAttribute("sex",redisTemplate.opsForHash().entries("user_"+userId).get("sex"));
            httpSession.setAttribute("trade",redisTemplate.opsForHash().entries("user_"+userId).get("trade"));
            httpSession.setAttribute("introduction",redisTemplate.opsForHash().entries("user_"+userId).get("introduction"));
            httpSession.setAttribute("registerDate",redisTemplate.opsForHash().entries("user_"+userId).get("registerDate"));
            return new Result(true,StatusCode.OK,"登陆成功");
        }else{
            return new Result(false,StatusCode.CodeError,"验证码错误");
        }
    }

    @PostMapping("loginSendCode/{phone}")
    public Result loginSendCode(@PathVariable("phone") String phone)
    {
        boolean flag = loginService.isExistPhone(phone);
        if(flag)
        {
            String code = registerService.getSmsCode(phone);
            registerService.sendCode(phone, code);
            return new Result(true, StatusCode.OK, "发送成功");
        }else{
            return new Result(false, StatusCode.ExistPhoneError,"该手机号未注册");
        }
    }
}
