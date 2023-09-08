package com.task.retry.domain;

import com.task.retry.entity.model.TaskDO;
import com.task.retry.enums.TaskState;
import com.task.retry.mapper.TaskMapper;

/**
 * Author: miracle
 * Date: 2023/9/7 22:09
 */
public class TaskDomain {

    private final TaskMapper taskMapper;
    private final TaskDO task;

    public TaskDomain(TaskMapper taskMapper, TaskDO task) {
        this.taskMapper = taskMapper;
        this.task = task;
    }

    public boolean ready() {
        this.task.setState(TaskState.WAIT.name());
        return taskMapper.updateById(task, TaskState.toWaitState()) > 0;
    }

    public boolean running() {
        this.task.setState(TaskState.RUNNING.name());
        // 处于running状态，就认为已经执行了一次
        this.task.setExecutedCount(task.getExecutedCount() + 1);
        return taskMapper.updateById(task, TaskState.toRunningState()) > 0;
    }

    public boolean finish() {
        this.task.setState(TaskState.FINISHED.name());
        return taskMapper.updateById(task, TaskState.toFinishedState()) > 0;
    }

    public boolean failed(String errorMessage) {
        this.task.setState(TaskState.FAILED.name());
        this.task.setErrorMessage(errorMessage);
        return taskMapper.updateById(task, TaskState.toFailedState()) > 0;
    }

    public boolean cancel() {
        this.task.setState(TaskState.CANCEL.name());
        return taskMapper.updateById(task, TaskState.toCancelState()) > 0;
    }

}
