package com.task.retry.enums;

/**
 * Author: miracle
 * Date: 2023/9/7 14:14
 */
public enum TaskState {

    /**
     * 等待
     */
    WAIT,
    /**
     * 运行中
     */
    RUNNING,
    /**
     * 完成
     */
    FINISHED,
    /**
     * 失败
     */
    FAILED,
    /**
     * 取消
     */
    CANCEL,

    ;
}
