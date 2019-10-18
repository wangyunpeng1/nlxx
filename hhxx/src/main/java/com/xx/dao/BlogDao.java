package com.xx.dao;

import com.xx.vo.Blog;
import com.xx.vo.BlogInfo;
import com.xx.vo.BlogSum;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BlogDao
{
    public void insertBlogSum(BlogSum blogSum);

    public void insertBlogInfo(BlogInfo blogInfo);

    public void updateBlog(BlogInfo blogInfo);
}
