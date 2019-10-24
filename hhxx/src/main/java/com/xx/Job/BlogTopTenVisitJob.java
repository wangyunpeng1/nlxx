package com.xx.Job;

import com.xx.dao.BlogDao;
import com.xx.vo.BlogVisit;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

public class BlogTopTenVisitJob implements Job
{
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException
    {
        System.out.println("10个最高浏览统计中");
        redisTemplate.delete("TopTenBlogs_Visit");
        List<BlogVisit> info = blogDao.topTenVisit();
        for (int i=0;i<info.size();i++)
        {
            redisTemplate.opsForZSet().add("TopTenBlogs_Visit",info.get(i).getBlogId(),info.get(i).getVisit());
        }
    }
}
