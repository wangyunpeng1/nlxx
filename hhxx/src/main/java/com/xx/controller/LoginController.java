package com.xx.controller;

import com.xx.service.LoginService;
import com.xx.vo.Login;
import com.xx.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

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
        return new Result();
    }

    @PostMapping("phoneLogin")
    public Result phoneLogin(@PathVariable String phone)
    {
        return new Result();
    }

}
