package com.xx.service;

import com.xx.dao.UserInfoDao;
import com.xx.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserInfoService
{
    @Autowired
    private UserInfoDao userInfoDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //完善个人信息
    public void updateUserInfo(UserInfo userInfo)
    {
        userInfoDao.updateUserInfo(userInfo);
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"userName",userInfo.getUserName());
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"phone",userInfo.getPhone());
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"sex",userInfo.getSex());
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"registerDate",userInfo.getRegisterDate());
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"email",userInfo.getEmail());
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"trade",userInfo.getTrade());
        redisTemplate.opsForHash().put("user_"+userInfo.getUserId(),"introduction",userInfo.getIntroduction());
    }

    //取用户信息
    public Map getUserInfo(String userId)
    {
        Map<Object,Object> map = redisTemplate.opsForHash().entries("user_"+userId);
        System.out.println(map);
        return map;
    }

}
