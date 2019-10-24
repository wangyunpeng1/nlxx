package com.xx.Job;

import com.xx.dao.BlogDao;
import com.xx.vo.BlogFabulous;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 统计博客赞数最高的10个
 */
public class BlogTopTenFabulousJob implements Job
{
    @Autowired
    private BlogDao blogDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("10个最高点赞统计中");
        redisTemplate.delete("TopTenBlogs_Fabulous");
        List<BlogFabulous> info = blogDao.topTenFabulous();
        for (int i=0;i<info.size();i++)
        {
            redisTemplate.opsForZSet().add("TopTenBlogs_Fabulous",info.get(i).getBlogId(),info.get(i).getFabulous());
        }
    }
}
