package com.xx.service;

import com.xx.dao.BlogDao;
import com.xx.vo.Blog;
import com.xx.vo.BlogInfo;
import com.xx.vo.BlogSum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BlogService
{
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private RedisTemplate redisTemplate;

    //上传博客
    public void uploadBlog(Blog blog)
    {
        BlogSum blogSum = new BlogSum();
        BlogInfo blogInfo = new BlogInfo();
        blog.setBlogId(getId());
        //blogSum表
        blogSum.setBlogId(blog.getBlogId());
        blogSum.setComments(0);
        blogSum.setFabulos(0);
        blogSum.setCollections(0);
        blogSum.setVisit(0);
        blogDao.insertBlogSum(blogSum);
        //blogInfo表
        blogInfo.setBlogId(blog.getBlogId());
        blogInfo.setBlogName(blog.getBlogName());
        blogInfo.setBlogLabel(blog.getBlogLabel());
        blogInfo.setBlogType(blog.getBlogType());
        blogInfo.setCreateDate(getTime());
        blogDao.insertBlogInfo(blogInfo);
    }

    //修改博客
    public void updateBlog(Blog blog)
    {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setBlogId(blog.getBlogId());
        blogInfo.setBlogName(blog.getBlogName());
        blogInfo.setBlogLabel(blog.getBlogLabel());
        blogInfo.setBlogType(blog.getBlogType());
        blogDao.updateBlog(blogInfo);
    }
    //获取博客id
    public String getId()
    {
        boolean flag = redisTemplate.hasKey("blogId");
        if (flag)
        {
            String a =redisTemplate.opsForValue().get("blogId").toString();
            int b = Integer.parseInt(a)+1;
            redisTemplate.opsForValue().set("blogId",b);
        }else{
            redisTemplate.opsForValue().set("blogId",1);
        }
        String blogId =redisTemplate.opsForValue().get("blogId").toString();
        System.out.println(blogId);
        return blogId;
    }

    //获取当前时间
    public String getTime()
    {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        return dateFormat.format(date);
    }
}
