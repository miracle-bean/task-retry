package com.task.retry.impl;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.google.common.collect.Lists;
import com.task.retry.BaseTest;
import com.task.retry.TaskQuery;
import com.task.retry.entity.model.Task;
import org.assertj.core.api.Condition;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Author: miracle
 * Date: 2023/10/12 13:44
 */
public class TaskQueryImplTest extends BaseTest {

    @Autowired
    private TaskQuery taskQuery;

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByBusiness_empty() {
        String businessType = UUID.randomUUID().toString();
        String businessId = UUID.randomUUID().toString();
        List<Task> tasks = taskQuery.queryByBusiness(businessType, businessId);
        assertThat(tasks).isEmpty();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByBusiness_not_empty() {
        String businessType = "testDemo1";
        String businessId = "1";
        List<Task> tasks = taskQuery.queryByBusiness(businessType, businessId);
        assertThat(tasks).hasSize(1).are(new Condition<Task>() {
            @Override
            public boolean matches(Task value) {
                return value.getBusinessType().equals(businessType)
                        && value.getBusinessId().equals(businessId);
            }
        });
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByBusiness_onlyType_empty() {
        String businessType = UUID.randomUUID().toString();
        List<Task> tasks = taskQuery.queryByBusiness(businessType);
        assertThat(tasks).isEmpty();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByBusiness_onlyType_not_empty() {
        String businessType = "testDemo1";
        List<Task> tasks = taskQuery.queryByBusiness(businessType);
        assertThat(tasks).hasSize(1).are(new Condition<Task>() {
            @Override
            public boolean matches(Task value) {
                return value.getBusinessType().equals(businessType);
            }
        });
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_getById_null() {
        Task task = taskQuery.getById(999L);
        assertThat(task).isNotNull();
        assertThat(task.getId()).isNull();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_getById_success() {
        Task task = taskQuery.getById(1L);
        assertThat(task).isNotNull().has(new Condition<Task>() {
            @Override
            public boolean matches(Task value) {
                return value.getId().equals(1L);
            }
        });
    }


    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByIds_empty() {
        List<Task> tasks = taskQuery.queryByIds(Lists.newArrayList());
        assertThat(tasks).isEmpty();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByIds_not_find() {
        List<Task> tasks = taskQuery.queryByIds(Lists.newArrayList(999L, 1000L));
        assertThat(tasks).isEmpty();
    }

    @Test
    @DatabaseSetup(value = {"../../../../data/task_setup.xml"})
    public void test_queryByIds_success() {
        List<Task> tasks = taskQuery.queryByIds(Lists.newArrayList(1L, 2L));
        assertThat(tasks).hasSize(2).are(new Condition<Task>() {
            @Override
            public boolean matches(Task value) {
                return value.getId().equals(1L)
                        || value.getId().equals(2L);
            }
        });
    }

}