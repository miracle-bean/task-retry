package com.task.retry;

import com.task.retry.entity.model.Task;

import java.util.List;

/**
 * 任务查询
 * <p>
 * Author: miracle
 * Date: 2023/9/7 14:12
 */
public interface TaskQuery {

    List<Task> queryByBusiness(String businessType, String businessId);

    List<Task> queryByBusiness(String businessType);

}
