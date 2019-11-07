package com.xx.service;

import com.xx.dao.BlogDao;
import com.xx.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        UserBlog userBlog = new UserBlog();
        blog.setBlogId(getId());
        //blogSum表
        blogSum.setBlogId(blog.getBlogId());
        blogSum.setComments(0);
        blogSum.setFabulous(0);
        blogSum.setCollections(0);
        blogSum.setVisit(0);
        blogDao.insertBlogSum(blogSum);
        //blogInfo表
        blogInfo.setBlogId(blog.getBlogId());
        blogInfo.setBlogName(blog.getBlogName());
        blogInfo.setBlogLabel(blog.getBlogLabel());
        blogInfo.setBlogType(blog.getBlogType());
        blogInfo.setBlogContent(blog.getBlogContent());
        blogInfo.setCreateDate(getTime());
        blogDao.insertBlogInfo(blogInfo);
        //userBlogs表
        userBlog.setBlogId(blog.getBlogId());
        userBlog.setUserId(blog.getUserId());
        blogDao.insertUserBlog(userBlog);
        //userSum表
        blogDao.addUserBlogs(blog.getUserId());
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

    //查看博客
    public Blog lookBlog(String blogId)
    {
        BlogSum blogSum = blogDao.selectBlogSum(blogId);
        BlogInfo blogInfo = blogDao.selectBlogInfo(blogId);
        blogDao.addVisit(blogId);
        Blog blog = new Blog();
        blog.setBlogId(blogId);
        blog.setVisit(blogSum.getVisit());
        blog.setFabulous(blogSum.getFabulous());
        blog.setComments(blogSum.getComments());
        blog.setCollections(blogSum.getCollections());
        blog.setBlogName(blogInfo.getBlogName());
        blog.setBlogLabel(blogInfo.getBlogLabel());
        blog.setBlogType(blogInfo.getBlogType());
        blog.setCreateDate(blogInfo.getCreateDate());

        return blog;
    }

    //获取博客的收藏数
    public long selectCollections(String blogId)
    {
        long a = blogDao.selectCollections(blogId);
        return a;
    }

    //获取前10点赞的博客
    public Set<String> getTopTenFabulousBlogs()
    {
        Set<String> blogFabulous = redisTemplate.opsForZSet().range("TopTenBlogs_Fabulous",0,9);
        return blogFabulous;
    }

    //获取前10浏览的博客
    public Set<String> getTopTenVisitBlogs()
    {
        Set<String> blogVisits = redisTemplate.opsForZSet().range("TopTenBlogs_Visit",0,9);
        return blogVisits;
    }

    //获取前10被收藏的博客
    public Set<String> getTopTenCollectionsBlogs()
    {
        Set<String> blogCollections = redisTemplate.opsForZSet().range("TopTenBlogs_Collections",0,9);
        return blogCollections;
    }

    //id转博客
    public Map<String,String> getblogIdName(Set<String> infos)
    {
        Map<String,String> map = new HashMap<String, String>();
        for (int i=0;i<infos.size();i++)
        {
            map.put("" + i, blogDao.getBlogName(infos.toArray()[i].toString()));
        }
        return map;
    }
}
