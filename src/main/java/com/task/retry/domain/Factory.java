package com.task.retry.domain;

import com.task.retry.entity.model.TaskDO;
import com.task.retry.mapper.TaskMapper;

/**
 * Author: miracle
 * Date: 2023/9/7 22:16
 */
public class Factory {

    public TaskDomain create(TaskMapper taskMapper, TaskDO taskDO) {
        return new TaskDomain(taskMapper, taskDO);
    }

}
