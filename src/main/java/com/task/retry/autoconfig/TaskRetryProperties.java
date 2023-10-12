package com.task.retry.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Author: miracle
 * Date: 2023/9/8 10:13
 */
@Data
@ConfigurationProperties(prefix = "task-retry")
public class TaskRetryProperties {

    /**
     * 分发任务的颗粒度
     * 其实就是分页的大小，默认是100
     */
    private Integer particleSize = 100;
    /**
     * 任务最大重试此处，默认3次
     */
    private Integer maxRetryCount = 3;
    /**
     * 启动任务自动分发
     */
    private Boolean autoJob = Boolean.TRUE;

}
