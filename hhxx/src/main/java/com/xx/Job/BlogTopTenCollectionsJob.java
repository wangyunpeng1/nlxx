package com.xx.Job;

import com.xx.dao.BlogDao;
import com.xx.vo.BlogCollections;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;
/**
 * 统计博客被收藏数最高的10个
 */
public class BlogTopTenCollectionsJob implements Job {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private BlogDao blogDao;
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("10个最高点赞统计中");
        redisTemplate.delete("TopTenBlogs_Collections");
        List<BlogCollections> info = blogDao.topTenCollections();
        for (int i=0;i<info.size();i++)
        {
            redisTemplate.opsForZSet().add("TopTenBlogs_Collections",info.get(i).getBlogId(),info.get(i).getCollections());
        }
    }
}
