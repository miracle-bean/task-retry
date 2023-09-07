package com.task.retry.autoconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Author: miracle
 * Date: 2023/9/7 15:26
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "")
public class TaskRetryAutoConfig {
}
