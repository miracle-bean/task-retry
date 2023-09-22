package com.task.retry.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.task.retry.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

/**
 * Author: miracle
 * Date: 2023/9/22 15:33
 */
public class TaskOperatorImplTest extends BaseTest {

    @Autowired
    private TaskOperatorImpl taskOperator;

    @Test
    @DatabaseSetup(value = {"data/task_setup.xml"})
    public void test_register() {
        String businessType = UUID.randomUUID().toString();
        String businessId = UUID.randomUUID().toString();
        String payload = UUID.randomUUID().toString();
        taskOperator.register(businessType, businessId, payload);
    }

    @Test
    public void testRegister() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void reset() {
    }
}