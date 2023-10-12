package com.task.retry.schedule;

import com.task.retry.domain.TaskExecute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.StopWatch;

/**
 * Author: miracle
 * Date: 2023/9/7 15:42
 */
public class ScheduleJob {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleJob.class);
    private final TaskExecute taskExecute;
    private final StopWatch stopWatch = new StopWatch();

    public ScheduleJob(TaskExecute taskExecute) {
        this.taskExecute = taskExecute;
    }

    @Scheduled(cron = "0 0/1 * * * ? ")
    public void invoke() {
        logger.info("start taskDistribution");
        stopWatch.start();
        taskExecute.taskDistribution();
        stopWatch.stop();
        logger.info("end taskDistribution, time:{}", stopWatch.getLastTaskTimeMillis());
    }

}
