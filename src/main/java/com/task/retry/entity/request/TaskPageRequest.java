package com.task.retry.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Author: miracle
 * Date: 2023/9/7 15:44
 */
@Data
@Accessors(chain = true)
public class TaskPageRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = -5286193291117130492L;

    /**
     * id
     */
    private List<Long> idList;
    /**
     * @see com.task.retry.enums.TaskState
     */
    private List<String> stateList;
    /**
     * 任务类型，业务自定义
     */
    private String businessType;
    /**
     * 业务自定义id
     */
    private String businessId;
    /**
     * 下次执行时间
     */
    private LocalDateTime nextFireTime;
    /**
     * 已经执行次数小于最大执行次数
     */
    private Boolean isLessMaxTimes = Boolean.TRUE;

}
