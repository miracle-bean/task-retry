package com.task.retry.autoconfig;

import com.task.retry.TaskOperator;
import com.task.retry.TaskQuery;
import com.task.retry.domain.Factory;
import com.task.retry.domain.TaskExecute;
import com.task.retry.domain.impl.TaskExecuteImpl;
import com.task.retry.impl.TaskOperatorImpl;
import com.task.retry.impl.TaskQueryImpl;
import com.task.retry.mapper.TaskMapper;
import com.task.retry.schedule.TaskScheduleJob;
import lombok.Data;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Author: miracle
 * Date: 2023/9/7 15:26
 */
@Data
@Configuration
@MapperScan(basePackages = "com.task.retry.mapper")
@EnableConfigurationProperties({TaskRetryProperties.class})
public class TaskRetryAutoConfig {

    private final TaskRetryProperties properties;
    private final TaskMapper taskMapper;

    @Bean
    public Factory factory() {
        return new Factory();
    }

    @Bean
    public TaskExecute taskExecute(Factory factory) {
        return new TaskExecuteImpl(taskMapper, properties.getParticleSize(), factory);
    }

    @Bean
    public TaskOperator taskOperator(Factory factory) {
        return new TaskOperatorImpl(factory, taskMapper, properties);
    }

    @Bean
    public TaskQuery taskQuery() {
        return new TaskQueryImpl(taskMapper);
    }

    @Bean
    public TaskScheduleJob scheduleJob(TaskExecute taskExecute) {
        if (!properties.getAutoJob()) {
            return null;
        }
        return new TaskScheduleJob(taskExecute, properties);
    }

}
