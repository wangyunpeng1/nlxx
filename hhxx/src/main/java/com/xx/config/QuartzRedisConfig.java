package com.xx.config;

import com.xx.factory.JobFactory;
import com.xx.Job.RedisJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzRedisConfig
{
    @Bean(name = "JobRedis")
    public JobDetailFactoryBean jobRedis()
    {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        //关联自己的job
        factory.setJobClass(RedisJob.class);
        return factory;
    }
    @Bean(name = "TriggerRedis")
    public CronTriggerFactoryBean cronTriggerRedis(@Qualifier("JobRedis") JobDetailFactoryBean jobRedis)
    {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobRedis.getObject());
        factory.setCronExpression("0 0 0/1 * * ?"); //1小时执行一次
        return factory;
    }
    @Bean
    public SchedulerFactoryBean schedulerRedis(@Qualifier("TriggerRedis")CronTriggerFactoryBean cronTriggerRedis, JobFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerRedis.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
