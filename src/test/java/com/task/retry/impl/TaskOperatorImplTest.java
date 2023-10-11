package com.task.retry.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.google.common.collect.Lists;
import com.task.retry.BaseTest;
import com.task.retry.TaskOperator;
import com.task.retry.TaskQuery;
import com.task.retry.entity.model.Task;
import com.task.retry.enums.TaskState;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void test_register_success() {
        String businessType = UUID.randomUUID().toString();
        String businessId = UUID.randomUUID().toString();
        String payload = UUID.randomUUID().toString();
        taskOperator.register(businessType, businessId, payload);
        List<Task> tasks = taskQuery.queryByBusiness(businessType, businessId);
        assertThat(tasks).hasSize(1).are(new Condition<Task>() {
            @Override
            public boolean matches(Task task) {
                return task.getState().equals(TaskState.WAIT.name())
                        && task.getBusinessId().equals(businessId)
                        && task.getBusinessType().equals(businessType);
            }
        });
    }

    @Test
    public void test_register_only_businessType_success() {
        String businessType = UUID.randomUUID().toString();
        String payload = UUID.randomUUID().toString();
        taskOperator.register(businessType, payload);
        List<Task> tasks = taskQuery.queryByBusiness(businessType);
        assertThat(tasks).hasSize(1).are(new Condition<Task>() {
            @Override
            public boolean matches(Task task) {
                return task.getState().equals(TaskState.WAIT.name())
                        && task.getBusinessType().equals(businessType);
            }
        });
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void cancel_when_wait_success() {
        Long taskId = 1L;
        Task oldTask = taskQuery.getById(taskId);
        assertThat(oldTask).isNotNull().has(new Condition<Task>() {
            @Override
            public boolean matches(Task task) {
                return task.getState().equals(TaskState.WAIT.name());
            }
        });
        taskOperator.cancel(Lists.newArrayList(taskId));
        Task newTask = taskQuery.getById(taskId);
        assertThat(newTask).isNotNull().has(new Condition<Task>() {
            @Override
            public boolean matches(Task task) {
                return task.getState().equals(TaskState.CANCEL.name());
            }
        });
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void cancel_when_running_failed() {
//        taskOperator.cancel();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void cancel_when_finished_failed() {
//        taskOperator.cancel();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void cancel_when_cancel_failed() {
//        taskOperator.cancel();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void cancel_when_failed_success() {
//        taskOperator.cancel();
    }

    @Test
    public void reset() {
    }

}