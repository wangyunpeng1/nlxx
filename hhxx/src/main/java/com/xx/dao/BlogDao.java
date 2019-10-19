package com.xx.dao;

import com.xx.vo.BlogInfo;
import com.xx.vo.BlogSum;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BlogDao
{
    //插入博客各种总数
    public void insertBlogSum(BlogSum blogSum);
    //插入博客信息
    public void insertBlogInfo(BlogInfo blogInfo);
    //更改博客信息
    public void updateBlog(BlogInfo blogInfo);
    //增加博客赞
    public void addFabulous(@Param("blogId") String blogId);
    //减少博客赞
    public void reduceFabulous(@Param("blogId") String blogId);
    //增加博客收藏
    public void addCollections(@Param("blogId") String blogId);
    //减少博客收藏
    public void reduceCollections(@Param("blogId") String blogId);
}
