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

    public boolean toWait() {
        this.task.setState(TaskState.WAIT.name());
        return taskMapper.updateById(task) > 0;
    }

    public boolean toRun() {
        this.task.setState(TaskState.RUNNING.name());
        return taskMapper.updateById(task) > 0;
    }

    public boolean toFinish() {
        this.task.setState(TaskState.FINISHED.name());
        this.task.setExecutedCount(task.getExecutedCount() + 1);
        return taskMapper.updateById(task) > 0;
    }

    public boolean toFailed(String errorMessage) {
        this.task.setState(TaskState.FAILED.name());
        this.task.setExecutedCount(task.getExecutedCount() + 1);
        this.task.setErrorMessage(errorMessage);
        return taskMapper.updateById(task) > 0;
    }

    public boolean cancel() {
        this.task.setState(TaskState.CANCEL.name());
        return taskMapper.updateById(task) > 0;
    }

}
