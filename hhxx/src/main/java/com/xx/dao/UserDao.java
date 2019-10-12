package com.xx.dao;

import com.xx.vo.UserBlog;
import com.xx.vo.UserFans;
import com.xx.vo.UserSum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao
{
    public void userFlow(UserFans userFans);

    public void userBlogs(UserBlog userBlog);

    public void userCollectionBlogs(UserBlog userBlog);

    public void lookUserCollectionBlogs(UserBlog userBlog);

    public void userSum(@Param("userId") String userId);
}
