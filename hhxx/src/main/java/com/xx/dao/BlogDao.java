package com.xx.dao;

import com.xx.vo.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

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
    //博客浏览量+1
    public void addVisit(String blogId);
    //查看博客总
    public BlogSum selectBlogSum(String blogId);
    //查看博客信息
    public BlogInfo selectBlogInfo(String blogId);
    //增加博客赞
    public void addFabulous(@Param("blogId") String blogId);
    //减少博客赞
    public void reduceFabulous(@Param("blogId") String blogId);
    //增加博客收藏
    public void addCollections(@Param("blogId") String blogId);
    //减少博客收藏
    public void reduceCollections(@Param("blogId") String blogId);
    //查赞排行前10的博客
    public List<BlogFabulous> topTenFabulous();
    //查浏览量排行前10的博客
    public List<BlogVisit> topTenVisit();
    //查收藏量排行前10的博客
    public List<BlogCollections> topTenCollections();
    //查看博客名字
    public String getBlogName(@Param("blogId") String blogId);


}
