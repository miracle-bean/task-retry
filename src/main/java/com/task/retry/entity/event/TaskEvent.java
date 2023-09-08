package com.task.retry.entity.event;

import com.task.retry.entity.model.Task;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Author: miracle
 * Date: 2023/9/7 22:26
 */
@Data
public class TaskEvent implements Serializable {

    private static final long serialVersionUID = 3957255307413538624L;

    public TaskEvent(Task taskDO) {
        this.createTime = taskDO.getCreateTime();
        this.updateTime = taskDO.getUpdateTime();
        this.businessId = taskDO.getBusinessId();
        this.businessType = taskDO.getBusinessType();
        this.payload = taskDO.getPayload();
    }

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

}
