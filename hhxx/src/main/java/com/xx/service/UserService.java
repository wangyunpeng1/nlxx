package com.xx.service;

import com.xx.dao.BlogDao;
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
    private BlogDao blogDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //关注，粉丝数量+1
    public Boolean userFlow(UserFans userFans)
    {
        boolean bl;
        boolean flag = redisTemplate.opsForSet().isMember("star_"+userFans.getUserId(),"fans_"+userFans.getFansId());
        if (flag)
        {
            redisTemplate.opsForSet().remove("star_"+userFans.getUserId(),"fans_"+userFans.getFansId());
            bl = false;
        }else {
            redisTemplate.opsForSet().add("star_"+userFans.getUserId(),"fans_"+userFans.getFansId());
            bl = true;
        }
        return bl;
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
        blogDao.addCollections(userBlog.getBlogId());
    }

    //取消收藏
    public void userCancelCollectionBlogs(UserBlog userBlog)
    {
        userDao.userCancelCollectionBlogs(userBlog);
        blogDao.reduceCollections(userBlog.getBlogId());
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
            blogDao.reduceFabulous(userBlog.getBlogId());
            bl = false;
        }else {
            redisTemplate.opsForSet().add("fabulous_"+userBlog.getBlogId(),"user_"+userBlog.getUserId());
            blogDao.addFabulous(userBlog.getBlogId());
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
