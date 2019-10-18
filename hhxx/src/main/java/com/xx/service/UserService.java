package com.xx.service;

import com.xx.dao.UserDao;
import com.xx.vo.UserBlog;
import com.xx.vo.UserFans;
import com.xx.vo.UserSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //关注
    public void userFlow(UserFans userFans)
    {
        userDao.userFlow(userFans);
    }

    //查看用户博客
    public List<UserBlog> userBlogs(UserBlog userBlog)
    {
        return userDao.userBlogs(userBlog);
    }

    //收藏博客
    public void userCollectionBlogs(UserBlog userBlog)
    {
        userDao.userCollectionBlogs(userBlog);
    }

    //查看用户收藏的博客
    public List<UserBlog> lookUserCollectionBlogs(UserBlog userBlog)
    {
        return userDao.lookUserCollectionBlogs(userBlog);
    }

    //查看用户各种数
    public List<UserSum> userSum(String userId)
    {
        return userDao.userSum(userId);
    }

    //用户点赞博客
    public boolean fabulousBlog(UserBlog userBlog)
    {
        boolean bl;
        boolean flag = redisTemplate.opsForSet().isMember("fabulous_"+userBlog.getBlogId(),"user_"+userBlog.getUserId());
        if (flag)
        {
            redisTemplate.opsForSet().remove("fabulous_"+userBlog.getBlogId(),"user_"+userBlog.getUserId());
            bl = false;
        }else {
            redisTemplate.opsForSet().add("fabulous_"+userBlog.getBlogId(),"user_"+userBlog.getUserId());
            bl = true;
        }
        return bl;
    }

    //查看博客点赞（测试上述功能）
    public long selectBulous(String blogId)
    {
        long a = redisTemplate.opsForSet().size("fabulous_"+blogId);
        return a;
    }

}
