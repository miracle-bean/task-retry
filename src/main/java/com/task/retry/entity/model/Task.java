package com.task.retry.entity.model;

import com.task.retry.enums.TaskState;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Author: miracle
 * Date: 2023/9/7 14:12
 */
@Data
@Accessors(chain = true)
public class Task implements Serializable {

    private static final long serialVersionUID = 7920192769847820032L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 任务类型，业务自定义
     */
    private String businessType;
    /**
     * 业务自定义id
     */
    private String businessId;
    /**
     * 任务装配器
     */
    private String payload;
    /**
     * @see TaskState
     * 任务状态：WAIT、RUNNING、FINISHED、FAILED、CANCEL
     */
    private String state;
    /**
     * 最大重试次数
     */
    private Integer maxRetryCount;
    /**
     * 已经执行的次数
     */
    private Integer executedCount;
    /**
     * 下次执行时间
     */
    private LocalDateTime nextFireTime;
    /**
     * 执行过程中的报错信息
     */
    private String errorMessage;
    /**
     * 执行优先级，越大优先级越高
     */
    private Integer taskOrder;
    /**
     * 乐观锁
     */
    private Integer version;
    /**
     * 任务同步or异步
     * true：异步
     * false：同步
     */
    private Boolean async;

}
