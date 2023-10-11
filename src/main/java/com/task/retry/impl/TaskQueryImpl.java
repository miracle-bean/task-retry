package com.task.retry.impl;

import com.task.retry.TaskQuery;
import com.task.retry.domain.impl.TaskExecuteImpl;
import com.task.retry.entity.model.Task;
import com.task.retry.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Author: miracle
 * Date: 2023/10/11 11:00
 */
public class TaskQueryImpl implements TaskQuery {


    private static final Logger logger = LoggerFactory.getLogger(TaskExecuteImpl.class);
    private final TaskMapper taskMapper;

    public TaskQueryImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> queryByBusiness(String businessType, String businessId) {
        // todo
//        taskMapper.save();
        return null;
    }

    @Override
    public List<Task> queryByBusiness(String businessType) {
        return null;
    }
}
