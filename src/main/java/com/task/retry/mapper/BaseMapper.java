package com.task.retry.mapper;

import org.mybatis.spring.annotation.MapperScan;

/**
 * Author: miracle
 * Date: 2023/9/8 10:14
 */
@MapperScan(basePackages = "com.task.retry.mapper")
public interface BaseMapper<T> {
}
