package com.xx.controller;

import com.xx.service.RegisterService;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import com.xx.vo.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class RegisterController
{
    @Resource
    private RegisterService registerService;

    /**
     * 注册账户
     * @param user
     * @return
     */
    @PostMapping("register")
    public Result registerUser(@RequestBody User user)
    {
        boolean flag = registerService.isExistAccount(user.getAccount());
        if (flag)
        {
            return new Result(true, StatusCode.AccountError,"该账号已注册");
        }else{
            registerService.registerUser(user);
            return new Result(true,StatusCode.OK,"注册成功");
        }

    }

    /**
     * 发送验证码
     * @param phone
     * @return
     */
    @PostMapping("sendCode")
    public Result sendCode(@PathVariable String phone)
    {
        boolean flag = registerService.isExistPhone(phone);
        if(flag)
        {
            return new Result(true, StatusCode.ExistPhoneError,"该手机号已注册");
        }else {
            String code = registerService.getSmsCode(phone);
            registerService.sendCode(phone, code);
            return new Result(true, StatusCode.OK, "发送成功");
        }
    }
}
