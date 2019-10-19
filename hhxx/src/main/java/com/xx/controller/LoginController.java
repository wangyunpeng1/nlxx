package com.xx.controller;

import com.xx.service.LoginService;
import com.xx.vo.Login;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController
{
    @Autowired
    private LoginService loginService;

    /**
     * 账号密码登陆
     * @param login
     * @return
     */
    @PostMapping("login")
    public Result login(@RequestBody Login login)
    {
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
    @PostMapping("phoneLogin")
    public Result phoneLogin(@PathVariable String phone,@PathVariable String code)
    {
        boolean flag = loginService.phoneLogin(phone,code);
        if (flag)
        {
            return new Result(true,StatusCode.OK,"登陆成功");
        }else{
            return new Result(false,StatusCode.CodeError,"验证码错误");
        }
    }
}
