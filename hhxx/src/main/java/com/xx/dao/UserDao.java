package com.xx.dao;

import com.xx.vo.Blog;
import com.xx.vo.UserBlog;
import com.xx.vo.UserFans;
import com.xx.vo.UserSum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao
{
    public void userFlow(UserFans userFans);

    public List<UserBlog> userBlogs(UserBlog userBlog);

    public void userCollectionBlogs(UserBlog userBlog);

    public List<UserBlog> lookUserCollectionBlogs(UserBlog userBlog);

    public List<UserSum> userSum(@Param("userId") String userId);
}
