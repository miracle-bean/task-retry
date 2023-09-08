package com.task.retry.autoconfig;

import com.task.retry.config.TaskRetryProperties;
import com.task.retry.domain.Factory;
import com.task.retry.domain.TaskExecute;
import com.task.retry.domain.impl.TaskExecuteImpl;
import com.task.retry.mapper.TaskMapper;
import lombok.Data;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: miracle
 * Date: 2023/9/7 15:26
 */
@Data
@Configuration
@EnableConfigurationProperties({TaskRetryProperties.class})
public class TaskRetryAutoConfig {

    private final TaskRetryProperties properties;

    @Bean
    public Factory factory() {
        return new Factory();
    }

    @Bean
    public TaskExecute taskExecute(TaskMapper taskMapper, Factory factory) {
        return new TaskExecuteImpl(taskMapper, properties.getParticleSize(), factory);
    }


}
