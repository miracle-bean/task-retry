package com.task.retry.domain;

import com.task.retry.autoconfig.TaskRetryProperties;
import com.task.retry.entity.model.Task;
import com.task.retry.enums.TaskState;
import com.task.retry.mapper.TaskMapper;

import java.util.function.Supplier;

/**
 * Author: miracle
 * Date: 2023/9/7 22:09
 */
public class TaskDomain {

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

    public boolean ready() {
        this.task.setState(TaskState.WAIT.name());
        return taskMapper.updateById(task, TaskState.toWaitState()) > 0;
    }

    public boolean running() {
        this.task.setState(TaskState.RUNNING.name());
        // 处于running状态，就认为已经执行了一次
        this.task.setExecutedCount(task.getExecutedCount() + 1);
        return upVersion(() -> taskMapper.updateById(task, TaskState.toRunningState()) > 0);
    }

    public boolean finish() {
        this.task.setState(TaskState.FINISHED.name());
        return upVersion(() -> taskMapper.updateById(task, TaskState.toFinishedState()) > 0);
    }

    public boolean failed(String errorMessage) {
        this.task.setState(TaskState.FAILED.name());
        this.task.setErrorMessage(errorMessage);
        return upVersion(() -> taskMapper.updateById(task, TaskState.toFailedState()) > 0);
    }

    public boolean cancel() {
        this.task.setState(TaskState.CANCEL.name());
        return upVersion(() -> taskMapper.updateById(task, TaskState.toCancelState()) > 0);
    }

    public boolean reset() {
        this.task.setState(TaskState.CANCEL.name());
        this.task.setExecutedCount(0);
        this.task.setErrorMessage(null);
        return upVersion(() -> taskMapper.updateById(task, TaskState.toCancelState()) > 0);
    }

    private <T> T upVersion(Supplier<T> supplier) {
        // 乐观锁+1
        task.setNextVersion(task.getVersion() + 1);
        T apply = supplier.get();
        // 完成执行后，更新task的version
        task.setVersion(task.getNextVersion());
        return apply;
    }

}
