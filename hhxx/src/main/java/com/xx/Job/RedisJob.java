package com.xx.Job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class RedisJob implements Job
{
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("redis_job执行中");
    }
}
