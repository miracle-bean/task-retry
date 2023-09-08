package com.task.retry.domain.impl;

import com.task.retry.domain.Factory;
import com.task.retry.domain.TaskDomain;
import com.task.retry.domain.TaskExecute;
import com.task.retry.entity.dto.TaskEvent;
import com.task.retry.entity.model.TaskDO;
import com.task.retry.entity.request.TaskPageRequest;
import com.task.retry.enums.TaskState;
import com.task.retry.mapper.TaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * Author: miracle
 * Date: 2023/9/7 11:07
 */
public class TaskExecuteImpl implements TaskExecute, ApplicationContextAware {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecuteImpl.class);
    private final TaskMapper taskMapper;
    /**
     * 分发任务的颗粒度大小，其实就是每次翻页的颗粒度大小
     */
    private final Integer particleSize;
    private final Factory factory;
    private final ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 100, 1000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(1000));
    private ApplicationContext applicationContext;

    public TaskExecuteImpl(TaskMapper taskMapper, Integer particleSize, Factory factory) {
        this.taskMapper = taskMapper;
        this.particleSize = particleSize;
        this.factory = factory;
    }

    /**
     * 分发处于准备状态，或者失败状态（执行次数不超过最大执行次数）的任务
     */
    public void taskDistribution() {
        Integer startPage = 0;
        int offset;
        List<TaskDO> taskList;
        do {
            offset = startPage * particleSize;
            taskList = taskMapper.pageList(offset, particleSize, new TaskPageRequest()
                    .setStateList(TaskState.distributionTaskState())
                    .setIsLessMaxTimes(Boolean.TRUE));
            if (taskList == null || taskList.isEmpty()) {
                break;
            }
            // 下一页
            startPage++;
            this.pushTask(taskList);
        } while (!taskList.isEmpty());
    }

    private void pushTask(List<TaskDO> list) {
        if (list == null) {
            return;
        }
        // 需要异步执行的人任务
        List<TaskDO> asyncList = list.stream().filter(TaskDO::getAsync).collect(Collectors.toList());
        // 需要同步执行的任务
        List<TaskDO> commonList = list.stream().filter(TaskDO::getAsync).collect(Collectors.toList());
        // 异步执行开始
        asyncList.forEach(asyncTask -> executor.submit(() -> doPushEvent(asyncTask)));
        // 同步执行开始
        commonList.forEach(this::doPushEvent);
    }

    /**
     * 执行任务推送
     * 状态变更：
     * -> RUNNING -> FAILED
     * <p>or</p>
     * -> RUNNING -> FINISH
     */
    private void doPushEvent(TaskDO task) {
        // 创建领域
        TaskDomain domain = factory.create(taskMapper, task);
        boolean isRun = domain.running();
        if (!isRun) {
            logger.warn("状态更新失败,该任务已经被分,task:{}", task);
            return;
        }
        try {
            applicationContext.publishEvent(new TaskEvent(task));
        } catch (Exception e) {
            logger.error("push error task:{}", task, e);
            // 执行失败了
            domain.failed(e.getMessage());
            return;
        }
        domain.finish();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
