package com.task.retry.impl;

import com.task.retry.TaskOperator;
import com.task.retry.autoconfig.TaskRetryProperties;
import com.task.retry.domain.Factory;
import com.task.retry.domain.TaskDomain;
import com.task.retry.domain.impl.TaskExecuteImpl;
import com.task.retry.entity.model.Task;
import com.task.retry.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Author: miracle
 * Date: 2023/9/8 11:27
 */
public class TaskOperatorImpl implements TaskOperator {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecuteImpl.class);

    private final Factory factory;
    private final TaskMapper taskMapper;
    private final TaskRetryProperties properties;

    public TaskOperatorImpl(Factory factory, TaskMapper taskMapper, TaskRetryProperties properties) {
        this.factory = factory;
        this.taskMapper = taskMapper;
        this.properties = properties;
    }


    @Override
    public void register(String businessType, String businessId, String payload) {
        TaskDomain taskDomain = factory.create(taskMapper, new Task()
                .setBusinessType(businessType)
                .setBusinessId(businessId)
                .setPayload(payload), properties);
        taskDomain.register();
    }

    @Override
    public void register(String businessType, String payload) {
        register(businessType, null, payload);
    }

    @Override
    public void cancel(List<Long> taskIds) {
        if (taskIds == null || taskIds.isEmpty()) {
            logger.warn("cancel taskIds is empty");
            return;
        }
        taskIds.parallelStream().forEach(id -> factory.create(taskMapper, new Task()).cancel());
    }

    @Override
    public void reset(List<Long> taskIds) {
        if (taskIds == null || taskIds.isEmpty()) {
            logger.warn("reset taskIds is empty");
            return;
        }
        taskIds.parallelStream().forEach(id -> factory.create(taskMapper, new Task()).reset());
    }

}
