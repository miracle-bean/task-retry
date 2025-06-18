package com.task.retry.schedule;

import com.task.retry.autoconfig.TaskRetryProperties;
import com.task.retry.domain.TaskExecute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.util.StopWatch;

/**
 * Author: miracle
 * Date: 2023/9/7 15:42
 */
@EnableScheduling
public class TaskScheduleJob implements SchedulingConfigurer {

    private static final Logger logger = LoggerFactory.getLogger(TaskScheduleJob.class);
    private final TaskExecute taskExecute;
    private final TaskRetryProperties properties;

    public TaskScheduleJob(TaskExecute taskExecute, TaskRetryProperties properties) {
        this.taskExecute = taskExecute;
        this.properties = properties;
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(this::invoke, triggerContext -> {
            // 动态cron
            CronTrigger trigger = new CronTrigger(properties.getCron());
            return trigger.nextExecution(triggerContext);
        });
    }

    private void invoke() {
        logger.info("start taskDistribution");
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        taskExecute.taskDistribution();
        stopWatch.stop();
        logger.info("end taskDistribution, time:{}", stopWatch.getLastTaskTimeMillis());
    }

}
