package com.task.retry.domain;

import com.google.common.collect.Lists;
import com.task.retry.autoconfig.TaskRetryProperties;
import com.task.retry.entity.model.Task;
import com.task.retry.enums.TaskState;
import com.task.retry.mapper.TaskMapper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Random;

/**
 * Author: miracle
 * Date: 2023/9/7 22:09
 */
public class TaskDomain {

    private static final long BASE_DELAY_SECONDS = 1; // 基础等待时间（秒）
    private static final long MAX_JITTER_MS = 500;   // 随机抖动上限（毫秒）
    private static final Random random = new Random();

    private final TaskMapper taskMapper;
    private final Task task;
    private final TaskRetryProperties properties;

    public TaskDomain(TaskMapper taskMapper, Task task, TaskRetryProperties properties) {
        this.taskMapper = taskMapper;
        this.task = task;
        this.properties = properties;
    }

    public boolean register() {
        task.setState(TaskState.WAIT.name());
        task.setMaxRetryCount(properties.getMaxRetryCount());
        return taskMapper.save(task) > 0;
    }

    /**
     * only WAIT can be RUNNING
     */
    public boolean running() {
        this.task.setState(TaskState.RUNNING.name());
        // 处于running状态，就认为已经执行了一次
        this.task.setExecutedCount(task.getExecutedCount() + 1);
        boolean bool = taskMapper.updateById(task, TaskState.toRunningState()) > 0;
        if (bool) {
            // 更新成功，要为领域的版本号+1，以保证下次操作
            this.task.setVersion(task.getVersion() + 1);
        }
        return bool;
    }

    /**
     * only RUNNING can be finish
     */
    public boolean finish() {
        this.task.setState(TaskState.FINISHED.name());
        boolean bool = taskMapper.updateById(task, TaskState.toFinishedState()) > 0;
        if (bool) {
            // 更新成功，要为领域的版本号+1，以保证下次操作
            this.task.setVersion(task.getVersion() + 1);
        }
        return bool;
    }

    /**
     * only RUNNING can be failed
     */
    public boolean failed(String errorMessage) {
        this.task.setState(TaskState.FAILED.name());
        this.task.setErrorMessage(errorMessage);
        this.task.setNextFireTime(calculateNextFireTime(this.task.getExecutedCount()));
        boolean bool = taskMapper.updateById(task, TaskState.toFailedState()) > 0;
        if (bool) {
            // 更新成功，要为领域的版本号+1，以保证下次操作
            this.task.setVersion(task.getVersion() + 1);
        }
        return bool;
    }

    /**
     * only
     * WAIT -> CANCEL
     * FAILED -> CANCEL
     * other TaskState do nothing
     */
    public boolean cancel() {
        this.task.setState(TaskState.CANCEL.name());
        boolean bool = taskMapper.updateById(task, TaskState.toCancelState()) > 0;
        if (bool) {
            // 更新成功，要为领域的版本号+1，以保证下次操作
            this.task.setVersion(task.getVersion() + 1);
        }
        return bool;
    }

    /**
     * all TaskState can be reset(to WAIT)
     */
    public boolean reset() {
        this.task.setState(TaskState.WAIT.name());
        this.task.setExecutedCount(0);
        this.task.setErrorMessage(null);
        boolean bool = taskMapper.updateById(task, Lists.newArrayList()) > 0;
        if (bool) {
            // 更新成功，要为领域的版本号+1，以保证下次操作
            this.task.setVersion(task.getVersion() + 1);
        }
        return bool;
    }

    /**
     * 指数退避 + 随机抖动的核心逻辑
     */
    private static LocalDateTime calculateNextFireTime(Integer executedCount) {
        // 1. 指数退避计算基础等待时间
        long delaySeconds = (long) (BASE_DELAY_SECONDS * Math.pow(2, executedCount));
        // 2. 添加随机抖动（0~500毫秒）
        long jitterMs = random.nextInt((int) MAX_JITTER_MS);
        long totalDelayMs = delaySeconds * 1000 + jitterMs;
        return LocalDateTime.now().plus(totalDelayMs, ChronoUnit.MILLIS);
    }

}
