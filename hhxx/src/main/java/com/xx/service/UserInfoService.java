package com.xx.service;

import com.xx.dao.UserInfoDao;
import com.xx.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService
{
    @Autowired
    private UserInfoDao userInfoDao;

    //完善个人信息
    public void updateUserInfo(UserInfo userInfo)
    {
        userInfoDao.updateUserInfo(userInfo);
    }

}
