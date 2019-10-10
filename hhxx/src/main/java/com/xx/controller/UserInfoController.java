package com.xx.controller;

import com.xx.service.UserInfoService;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import com.xx.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserInfoController
{
    @Autowired
    private UserInfoService userInfoService;
    @PostMapping("userInfo")
    public Result updateUserInfo(@RequestBody UserInfo userInfo)
    {
        userInfoService.updateUserInfo(userInfo);
        return new Result(true, StatusCode.OK,"修改成功");
    }
}
