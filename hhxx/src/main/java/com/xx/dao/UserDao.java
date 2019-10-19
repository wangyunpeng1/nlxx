package com.xx.dao;

import com.xx.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao
{
    //关注
    public void userFlow(UserFans userFans);
    //查看用户的博客
    public List<UserBlog> userBlogs(UserBlog userBlog);
    //用户收藏博客
    public void userCollectionBlogs(UserBlog userBlog);
    //用户取消收藏博客
    public void userCancelCollectionBlogs(UserBlog userBlog);
    //用户查看收藏的博客
    public List<UserBlog> lookUserCollectionBlogs(UserBlog userBlog);
    //查看用户的各种总数
    public List<UserSum> userSum(@Param("userId") String userId);
    //获取所有用户Id
    public List<String> userId();
    //获取用户所有博客Id
    public List<String> blogId(@Param("userId") String userId);
    //修改用户各种总数
    public void updateUserSum(UserSum userSum);
}
