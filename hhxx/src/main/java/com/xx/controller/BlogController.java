package com.xx.controller;

import com.xx.service.BlogService;
import com.xx.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.Map;

@RestController
@RequestMapping("pages")
public class BlogController
{
    @Autowired
    private BlogService blogService;

    /**
     * 查看博客
     * @param blogId
     * @return
     */
    @PostMapping("lookBlogId/{blogId}")
    public Result lookBlogId(@PathVariable("blogId") String blogId)
    {
        return new Result(true, StatusCode.OK,"查看成功",blogService.lookBlog(blogId));
    }

    /**
     * 获取前10点赞的博客
     * @return
     */
    @PostMapping("getTopTenFabulousBlogs")
    public Result getTopTenFabulousBlogs()
    {
        Set<String> blogFabulous = blogService.getTopTenFabulousBlogs();
        //id转博客
        Map<String,String> set = blogService.getblogIdName(blogFabulous);
        return new Result(true,StatusCode.OK,"查询成功",set);
    }

    /**
     * 获取前10浏览的博客
     * @return
     */
    @PostMapping("getTopTenVisitBlogs")
    public Result getTopTenVisitBlogs()
    {
        Set<String> blogVisit = blogService.getTopTenVisitBlogs();
        //id转博客
        Map<String,String> set = blogService.getblogIdName(blogVisit);
        return new Result(true,StatusCode.OK,"查询成功",set);
    }
    /**
     * 获取前10收藏的博客
     * @return
     */
    @PostMapping("getTopTenCollectionsBlogs")
    public Result getTopTenCollectionsBlogs()
    {
        Set<String> blogCollections = blogService.getTopTenCollectionsBlogs();
        //id转博客
        Map<String,String> set = blogService.getblogIdName(blogCollections);
        return new Result(true,StatusCode.OK,"查询成功",set);
    }
}
