package com.lc.lcserve.config;

import com.lc.lcserve.cron.AdminOrderStatusSqlJob;
import com.lc.lcserve.cron.SearchIndexSqlJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 构建配置类
 */
@Configuration
public class QuartzConfig {


    @Bean
    public JobDetail searchIndexJob(){
        return JobBuilder.newJob(SearchIndexSqlJob.class).storeDurably().build();
    }

    @Bean
    public JobDetail adminOrderStatusJob(){
        return JobBuilder.newJob(AdminOrderStatusSqlJob.class).storeDurably().build();
    }

    /**
     * 定时任务用于添加用户的余额
     * @return
     */
    @Bean
    public Trigger triggerToQueueMilvus(){
        //每隔10秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");

        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("syncUserJobTrigger","group1")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .forJob(searchIndexJob())//关联上述的JobDetail
                .build();
        return trigger;
    }


    /**
     * 定时任务用于批量处理订单
     * @return
     */
    @Bean
    public Trigger triggerToQueueOrder(){
        //每隔10秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0/30 * * * * ?");

        //创建触发器
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("searchIndexJob","group1")//给Trigger起个名字
                .withSchedule(cronScheduleBuilder)
                .forJob(adminOrderStatusJob())//关联上述的JobDetail
                .build();
        return trigger;
    }

}
