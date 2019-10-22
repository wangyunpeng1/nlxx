package com.xx.config;

import com.xx.Job.BlogTopTenFabulousJob;
import com.xx.factory.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 一天更新一次，赞数前10的微博，每天12点
 */
@Configuration
public class QuartzBlogTopTenFabulousConfig
{
    @Bean(name = "JobBlogTopTenFabulous")
    public JobDetailFactoryBean jobBlogTopTenFabulous()
    {
        JobDetailFactoryBean detailFactory = new JobDetailFactoryBean();
        detailFactory.setJobClass(BlogTopTenFabulousJob.class);
        return detailFactory;
    }

    @Bean(name = "TriggerBlogTopTenFabulous")
    public CronTriggerFactoryBean cronTriggerBlogTopTenFabulous(@Qualifier("JobBlogTopTenFabulous") JobDetailFactoryBean jobDetailFactoryBean)
    {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0 0 12 * * ?");
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerBlogTopTenFabulous(@Qualifier("TriggerBlogTopTenFabulous")CronTriggerFactoryBean cronTriggerBlogTopTenFabulous, JobFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerBlogTopTenFabulous.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
