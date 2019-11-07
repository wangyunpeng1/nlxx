package com.xx.Job;

import com.xx.dao.UserDao;
import com.xx.vo.UserSum;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

/**
 * 定时更新用户的总量
 */
public class UserSumJob implements Job
{
    @Autowired
    private UserDao userDao;
    @Autowired
    private RedisTemplate redisTemplate;
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        System.out.println("用户总量统计中");

        List<String> info = userDao.userId();
        for (int i=0;i<info.size();i++)
        {
            UserSum userSum = new UserSum();
            String userId = info.get(i);
            //总粉丝
            int fansSum = redisTemplate.opsForSet().size("star_"+userId).intValue();
            userSum.setUserId(userId);
            userSum.setFans(fansSum);
            List<String> blogIdInfo = userDao.blogId(userId);
            //总赞
            int sum = 0;
            for (int j=0;j<blogIdInfo.size();j++)
            {
                String blogId = blogIdInfo.get(j);
                int a = redisTemplate.opsForSet().size("fabulous_"+blogId).intValue();
                sum = sum + a;
            }
            userSum.setFabulous(sum);
            userDao.updateUserSum(userSum);
        }
    }
}
