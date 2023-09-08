package com.task.retry.domain.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.task.retry.BaseTest;
import com.task.retry.domain.TaskExecute;
import com.task.retry.listener.ListenerItem;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

/**
 * Author: miracle
 * Date: 2023/9/8 10:27
 */
@Import({ListenerItem.class})
public class TaskExecuteImplTest extends BaseTest {

    @Autowired
    private TaskExecute taskExecute;

    @Test
    @DatabaseSetup(value = {"data/task_setup.xml"})
    public void taskDistribution() {
        taskExecute.taskDistribution();
    }

}