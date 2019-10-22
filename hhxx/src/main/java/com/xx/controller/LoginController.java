package com.xx.controller;

import com.xx.service.LoginService;
import com.xx.service.RegisterService;
import com.xx.vo.Login;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pages")
public class LoginController
{
    @Autowired
    private LoginService loginService;
    @Autowired
    private RegisterService registerService;

    /**
     * 账号密码登陆
     * @param login
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Login login)
    {
        System.out.println("login登陆");
        boolean flag = loginService.accountLogin(login);
        if (flag)
        {
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
    public Result phoneLogin(@PathVariable String phone,@PathVariable String code)
    {
        System.out.println("phoneLogin手机号登陆");
        boolean flag = loginService.phoneLogin(phone,code);
        if (flag)
        {
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
