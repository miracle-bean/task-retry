package com.task.retry.entity.request;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Author: miracle
 * Date: 2023/9/7 15:44
 */
@Data
@Accessors(chain = true)
public class QueryTaskRequest implements Serializable {

    @Serial
    private static final long serialVersionUID = 1646935682095649320L;

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

}
