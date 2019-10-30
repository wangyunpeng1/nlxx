package com.xx.controller;

import com.xx.dao.BlogDao;
import com.xx.service.BlogService;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
