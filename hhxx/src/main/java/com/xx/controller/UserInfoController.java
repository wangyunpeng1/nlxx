package com.xx.controller;

import com.xx.service.UserInfoService;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import com.xx.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("pages")
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
     * @param session
     * @return
     */
    @GetMapping("getUserInfo")
    public Result getUserInfo(HttpSession session)
    {
        String userId = session.getAttribute("userId").toString();
         Map<Object,Object> userInfos = userInfoService.getUserInfo(userId);
        System.out.println(userInfos.get("userName"));
        return new Result(true,StatusCode.OK,"获取成功",userInfos);
    }
}
