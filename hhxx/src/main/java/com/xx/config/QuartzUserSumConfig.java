package com.xx.config;

import com.xx.factory.JobFactory;
import com.xx.Job.UserSumJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 用户总量更新config
 */
@Configuration
public class QuartzUserSumConfig
{
    /**
     * 创建Job
     * @return
     */
    @Bean(name = "jobUserSum")
    public JobDetailFactoryBean jobMysql()
    {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        //关联自己的job
        factory.setJobClass(UserSumJob.class);
        return factory;
    }

    /**
     * 创建Trigger
     * @param jobUserSum
     * @return
     */
    @Bean(name = "TriggerUserSum")
    public CronTriggerFactoryBean cronTriggerMysql(@Qualifier("jobUserSum") JobDetailFactoryBean jobUserSum)
    {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobUserSum.getObject());
        //每小时触发一次
        factory.setCronExpression("0 0 0/1 * * ?");
        return factory;
    }

    /**
     * 创建Scheduler对象
     * @param cronTriggerUserSum
     * @param jobFactory
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerMysql(@Qualifier("TriggerUserSum")CronTriggerFactoryBean cronTriggerUserSum, JobFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerUserSum.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
