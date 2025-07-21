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

    /**
     * 通过businessType和businessId查询任务
     * <p>
     * if(StringUtils.isBlank(businessType)) return Lists.newArrayList();
     * </p>
     *
     * @param businessType businessType 必填
     * @param businessId   businessId 非必填
     * @return List
     */
    List<Task> queryByBusiness(String businessType, String businessId);

    /**
     * 通过businessType查询任务
     * <p>
     * if(StringUtils.isBlank(businessType)) return Lists.newArrayList();
     * </p>
     *
     * @param businessType businessType必填
     * @return List
     */
    List<Task> queryByBusiness(String businessType);

    Task getById(Long id);

    List<Task> queryByIds(List<Long> ids);

    /**
     * 把businessType + groupId认为是一个整体任务
     * 是否还有除了已经完全失败的任务，比如：失败的但还有充实次数，或者是运行中的
     * 完全失败：所有任务都已经执行完毕，但有失败的
     */
    Boolean hasNotCompleteFailed(String businessType, String groupId);

}
