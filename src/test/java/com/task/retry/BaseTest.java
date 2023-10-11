package com.task.retry;

import com.github.springtestdbunit.TransactionDbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DbUnitConfiguration;
import com.task.retry.autoconfig.TaskRetryAutoConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockitoTestExecutionListener;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;

/**
 * Author: miracle
 * Date: 2023/9/8 11:05
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Import({TaskRetryAutoConfig.class})
@SpringBootTest(classes = TaskRetryTestApplication.class)
@ActiveProfiles
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionDbUnitTestExecutionListener.class,
        MockitoTestExecutionListener.class})
@Rollback
@Transactional
@DbUnitConfiguration
public abstract class BaseTest {

}
