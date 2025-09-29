package com.task.retry.job;

import com.aizuda.snailjob.client.job.core.annotation.JobExecutor;
import com.aizuda.snailjob.client.job.core.dto.JobArgs;
import com.aizuda.snailjob.client.model.ExecuteResult;
import com.task.retry.domain.TaskExecute;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;

/**
 * Author: miracle
 * Date: 2025/9/29 下午4:25
 */
@Slf4j
@JobExecutor(name = "taskRetryJob")
public class SnailJob {

    private final TaskExecute taskExecute;

    public SnailJob(TaskExecute taskExecute) {
        this.taskExecute = taskExecute;
    }

    public ExecuteResult jobExecute(JobArgs jobArgs) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        taskExecute.taskDistribution();
        stopWatch.stop();
        return ExecuteResult.success();
    }

}
