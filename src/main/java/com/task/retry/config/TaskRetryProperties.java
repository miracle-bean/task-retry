package com.task.retry.config;

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

}
