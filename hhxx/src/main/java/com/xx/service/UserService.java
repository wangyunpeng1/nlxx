package com.xx.service;

import com.xx.dao.UserDao;
import com.xx.vo.UserBlog;
import com.xx.vo.UserFans;
import com.xx.vo.UserSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;

    //关注
    public void userFlow(UserFans userFans)
    {
        userDao.userFlow(userFans);
    }

    //查看用户博客
    public void userBlogs(UserBlog userBlog)
    {
        userDao.userBlogs(userBlog);
    }

    //收藏博客
    public void userCollectionBlogs(UserBlog userBlog)
    {
        userDao.userCollectionBlogs(userBlog);
    }

    //查看用户收藏的博客
    public void lookUserCollectionBlogs(UserBlog userBlog)
    {
        userDao.lookUserCollectionBlogs(userBlog);
    }

    //查看用户各种数
    public void userSum(String userId)
    {
        userDao.userSum(userId);
    }
}
