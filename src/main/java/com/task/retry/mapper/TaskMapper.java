package com.task.retry.mapper;

import com.task.retry.entity.model.Task;
import com.task.retry.entity.request.QueryTaskRequest;
import com.task.retry.entity.request.TaskPageRequest;

import java.util.List;

/**
 * Author: miracle
 * Date: 2023/10/12 20:40
 */
public interface TaskMapper {

    List<Task> pageList(Integer offset,
                        Integer pageSize,
                        TaskPageRequest request);

    List<Task> queryList(QueryTaskRequest request);

    int save(Task task);

    Task getById(Long id);

    int updateById(Task task, List<String> stateList);

    int selectNotCompleteCount(String businessType, String groupId);

}