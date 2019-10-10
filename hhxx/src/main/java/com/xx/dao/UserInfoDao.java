package com.xx.dao;

import com.xx.vo.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserInfoDao
{
    public void updateUserInfo(UserInfo userInfo);
}
