package com.task.retry.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.task.retry.BaseTest;
import com.task.retry.TaskOperator;
import com.task.retry.TaskQuery;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Author: miracle
 * Date: 2023/9/22 15:33
 */
public class TaskOperatorImplTest extends BaseTest {

    @Autowired
    private TaskOperator taskOperator;
    @Autowired
    private TaskQuery taskQuery;

    @Test
    @DatabaseSetup(value = {"data/task_setup.xml"})
    public void test_register() {
        String businessType = UUID.randomUUID().toString();
        String businessId = UUID.randomUUID().toString();
        String payload = UUID.randomUUID().toString();
        taskOperator.register(businessType, businessId, payload);
        // todo
//        Task task = taskQuery.queryByBusiness(businessType, businessId);
//        assertThat(task).isNotNull();
    }

    @Test
    public void test_register_only_businessType() {
        String businessType = UUID.randomUUID().toString();
        String payload = UUID.randomUUID().toString();
        taskOperator.register(businessType, payload);
        // todo
    }

    @Test
    public void cancel() {
    }

    @Test
    public void reset() {
    }

}