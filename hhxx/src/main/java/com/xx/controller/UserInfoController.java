package com.xx.controller;

import com.xx.service.UserInfoService;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import com.xx.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserInfoController
{
    @Autowired
    private UserInfoService userInfoService;

    /**
     * 更改个人信息
     * @param userInfo
     * @return
     */
    @PostMapping("userInfo")
    public Result updateUserInfo(@RequestBody UserInfo userInfo)
    {
        userInfoService.updateUserInfo(userInfo);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    @PostMapping("getUserInfo/{userId}")
    public Result getUserInfo(@PathVariable String userId)
    {
         Map<Object,Object> userInfos = userInfoService.getUserInfo(userId);
        return new Result(true,StatusCode.OK,"获取成功",userInfos);
    }
}
