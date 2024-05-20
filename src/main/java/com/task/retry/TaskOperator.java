package com.task.retry;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 操作任务者，比如注册、作废、重置任务等
 * <p>
 * Author: miracle
 * Date: 2023/9/7 14:11
 */
public interface TaskOperator {

    /**
     * 注册一个任务
     *
     * @param businessType 业务侧用于区分任务类型，必须
     * @param businessId   业务侧用于区分任务的id，非必须
     * @param payload      业务侧存放任务相关数据（比如方法请求入参），必须
     */
    void register(String businessType, String businessId, LocalDateTime nextFireTime, String payload);

    /**
     * 注册一个任务
     */
    void register(String businessType, String businessId, String payload);

    /**
     * 注册一个任务
     */
    void register(String businessType, String payload);


    /**
     * 注册一个任务
     */
    void register(String businessType, LocalDateTime nextFireTime, String payload);

    /**
     * 取消任务，被取消的任务将不会再执行
     * <p></p>
     * 处于RUNNING中的任务，被取消后，可能被执行一次
     *
     * @param taskIds 必须
     */
    void cancel(List<Long> taskIds);

    /**
     * 重置任务，任务重置回WAIT状态，且已执行次数重置为零
     * <p></p>
     *
     * @param taskIds 必须
     */
    void reset(List<Long> taskIds);

}
