package com.task.retry.domain;

import com.task.retry.autoconfig.TaskRetryProperties;
import com.task.retry.entity.model.Task;
import com.task.retry.mapper.TaskMapper;

/**
 * Author: miracle
 * Date: 2023/9/7 22:16
 */
public class Factory {

    public TaskDomain create(TaskMapper taskMapper, Task taskDO) {
        return new TaskDomain(taskMapper, taskDO, null);
    }

    public TaskDomain create(TaskMapper taskMapper, Task taskDO, TaskRetryProperties properties) {
        return new TaskDomain(taskMapper, taskDO, properties);
    }

}
