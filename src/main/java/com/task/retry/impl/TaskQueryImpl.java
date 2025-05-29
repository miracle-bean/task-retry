package com.task.retry.impl;

import com.google.common.collect.Lists;
import com.task.retry.TaskQuery;
import com.task.retry.entity.model.Task;
import com.task.retry.entity.request.QueryTaskRequest;
import com.task.retry.mapper.TaskMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

/**
 * Author: miracle
 * Date: 2023/10/11 11:00
 */
public class TaskQueryImpl implements TaskQuery {

    private final TaskMapper taskMapper;

    public TaskQueryImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }

    @Override
    public List<Task> queryByBusiness(String businessType, String businessId) {
        if (StringUtils.isBlank(businessType)) {
            return Lists.newArrayList();
        }
        return Optional.ofNullable(taskMapper.queryList(new QueryTaskRequest()
                        .setBusinessType(businessType)
                        .setBusinessId(businessId)))
                .orElse(Lists.newArrayList());
    }

    @Override
    public List<Task> queryByBusiness(String businessType) {
        return queryByBusiness(businessType, null);
    }

    @Override
    public Task getById(Long id) {
        if (id == null) {
            return new Task();
        }
        List<Task> tasks = queryByIds(Lists.newArrayList(id));
        if (CollectionUtils.isEmpty(tasks)) {
            return new Task();
        }
        return tasks.get(0);
    }

    @Override
    public List<Task> queryByIds(List<Long> ids) {
        if (ids.isEmpty()) {
            return Lists.newArrayList();
        }
        return Optional.ofNullable(taskMapper.queryList(new QueryTaskRequest()
                        .setIdList(ids)))
                .orElse(Lists.newArrayList());
    }
}
