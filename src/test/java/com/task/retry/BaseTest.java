package com.task.retry;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.task.retry.autoconfig.TaskRetryAutoConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;

/**
 * Author: miracle
 * Date: 2023/9/8 11:05
 */
@RunWith(SpringRunner.class)
@Import({TaskRetryAutoConfig.class})
@SpringBootTest(classes = TaskRetryTestApplication.class)
@ActiveProfiles
@TestExecutionListeners({
        DbUnitTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        DependencyInjectionTestExecutionListener.class})
public abstract class BaseTest {

}
