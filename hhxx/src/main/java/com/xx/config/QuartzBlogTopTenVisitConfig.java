package com.xx.config;

import com.xx.Job.BlogTopTenVisitJob;
import com.xx.factory.JobFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 *统计浏览量前10的博客，每天12点更新
 */
@Configuration
public class QuartzBlogTopTenVisitConfig
{
    @Bean(name = "JobBlogTopTenVisit")
    public JobDetailFactoryBean jobBlogTopTenVisit()
    {
        JobDetailFactoryBean detailFactory = new JobDetailFactoryBean();
        detailFactory.setJobClass(BlogTopTenVisitJob.class);
        return detailFactory;
    }

    @Bean(name = "TriggerBlogTopTenVisit")
    public CronTriggerFactoryBean cronTriggerBlogTopTenVisit(@Qualifier("JobBlogTopTenVisit") JobDetailFactoryBean jobDetailFactoryBean)
    {
        CronTriggerFactoryBean factoryBean = new CronTriggerFactoryBean();
        factoryBean.setJobDetail(jobDetailFactoryBean.getObject());
        factoryBean.setCronExpression("0 0 12 * * ?");
        return factoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerBlogTopTenVisit(@Qualifier("TriggerBlogTopTenVisit")CronTriggerFactoryBean cronTriggerBlogTopTenVisit, JobFactory jobFactory)
    {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        //关联trigger
        factory.setTriggers(cronTriggerBlogTopTenVisit.getObject());
        factory.setJobFactory(jobFactory);
        return factory;
    }
}
