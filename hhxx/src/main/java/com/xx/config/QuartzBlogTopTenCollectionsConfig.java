package com.xx.config;

import com.xx.Job.BlogTopTenCollectionsJob;
import com.xx.factory.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *被收藏数前10的微博，每天12点更新
 */
@Configuration
public class QuartzBlogTopTenCollectionsConfig {
    @Bean(name = "JobBlogTopTenCollections")
    public JobDetailFactoryBean jobBlogTopTenCollections()
    {
        JobDetailFactoryBean detailFactory = new JobDetailFactoryBean();
        detailFactory.setJobClass(BlogTopTenCollectionsJob.class);
        return detailFactory;
    }

    @Bean(name = "TriggerBlogTopTenCollections")
    public CronTriggerFactoryBean cronTriggerBlogTopTenCollections(@Qualifier("JobBlogTopTenCollections") JobDetailFactoryBean jobDetailFactoryBean)
    {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0 0 12 * * ?");
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerBlogTopTenCollections(@Qualifier("TriggerBlogTopTenCollections")CronTriggerFactoryBean cronTriggerBlogTopTenCollections, JobFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerBlogTopTenCollections.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
