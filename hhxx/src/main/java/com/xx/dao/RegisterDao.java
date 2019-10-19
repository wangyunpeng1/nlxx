package com.xx.dao;

import com.xx.vo.User;
import com.xx.vo.UserInfo;
import com.xx.vo.UserSum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegisterDao
{
    //判断手机号是否存在
    public boolean isExistPhone(@Param("phone") String phone);
    //判断账号是否存在
    public boolean isExistAccount(@Param("account") String account);
    //注册用户
    public void registerUser(User user);
    //注册用户信息
    public void registerUserInfo(UserInfo userInfo);
    //注册用户各种总数
    public void registerUserSum(UserSum userSum);

}
