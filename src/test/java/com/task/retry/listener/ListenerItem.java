package com.task.retry.listener;

import com.task.retry.domain.impl.TaskExecuteImpl;
import com.task.retry.entity.event.TaskEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * Author: miracle
 * Date: 2023/9/8 10:50
 */
@Component
public class ListenerItem {

    private static final Logger logger = LoggerFactory.getLogger(TaskExecuteImpl.class);

    @EventListener(condition = "T(com.task.retry.entity.event.TaskEvent).businessType.equals(#taskEvent.businessType)")
    public void handler(TaskEvent taskEvent) {
        logger.info("taskEvent:{}", taskEvent);
    }

}
