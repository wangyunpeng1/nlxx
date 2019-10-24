package com.xx.controller;

import com.xx.service.ESService;
import com.xx.vo.ESBlog;
import com.xx.vo.PageResult;
import com.xx.vo.Result;
import com.xx.vo.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ESController
{
    @Autowired
    private ESService esService;

    @GetMapping("searchBlog/{words}")
    public Result findByBlogIdOrBlogName(@PathVariable("words") String words)
    {
        System.out.println("搜索："+words);
        int start = 0;
        int pageSize = 10;
        Page<ESBlog> blogs = esService.findByBlogIdAndBlogNameLikeAndBlogLabel(words,start,pageSize);
        return new Result(true, StatusCode.OK,"查询成功",new PageResult<ESBlog>(blogs.getTotalElements(),blogs.getContent()));
    }
}
