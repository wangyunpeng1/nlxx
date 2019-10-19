package com.xx.dao;

import com.xx.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoDao
{
    //更改用户信息
    public void updateUserInfo(UserInfo userInfo);
}
