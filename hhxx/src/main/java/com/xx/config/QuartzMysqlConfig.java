package com.xx.config;

import com.xx.factory.JobFactory;
import com.xx.Job.MysqlJob;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

@Configuration
public class QuartzMysqlConfig
{
    /**
     * 创建Job
     * @return
     */
    @Bean(name = "jobMysql")
    public JobDetailFactoryBean jobMysql()
    {
        JobDetailFactoryBean factory = new JobDetailFactoryBean();
        //关联自己的job
        factory.setJobClass(MysqlJob.class);
        return factory;
    }

    /**
     * 创建Trigger
     * @param jobMysql
     * @return
     */
    @Bean(name = "TriggerMysql")
    public CronTriggerFactoryBean cronTriggerMysql(@Qualifier("jobMysql") JobDetailFactoryBean jobMysql)
    {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean();
        factory.setJobDetail(jobMysql.getObject());
        factory.setCronExpression("0/10 * * * * ?");
        return factory;
    }

    /**
     * 创建Scheduler对象
     * @param cronTriggerMysql
     * @param jobFactory
     * @return
     */
    @Bean
    public SchedulerFactoryBean schedulerMysql(@Qualifier("TriggerMysql")CronTriggerFactoryBean cronTriggerMysql, JobFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerMysql.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
