package com.task.retry.enums;

import com.google.common.collect.Lists;

import java.util.List;

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

    /**
     * 获取可以分发任务的任务状态集合
     * 即：TaskState.WAIT && TaskState.FAILED
     */
    public static List<String> distributionTaskState() {
        return Lists.newArrayList(WAIT.name(), FAILED.name());
    }

    /**
     * 可以转为[WAIT]状态的任务状态集合
     */
    public static List<String> toWaitState() {
        return Lists.newArrayList(RUNNING.name(), FINISHED.name(), FAILED.name(), CANCEL.name());
    }

    /**
     * 可以转为[RUNNING]状态的任务状态集合
     */
    public static List<String> toRunningState() {
        return Lists.newArrayList(WAIT.name(), FAILED.name());
    }

    /**
     * 可以转为[FINISHED]状态的任务状态集合
     */
    public static List<String> toFinishedState() {
        return Lists.newArrayList(RUNNING.name());
    }

    /**
     * 可以转为[FAILED]状态的任务状态集合
     */
    public static List<String> toFailedState() {
        return Lists.newArrayList(RUNNING.name());
    }

    /**
     * 可以转为[CANCEL]状态的任务状态集合
     */
    public static List<String> toCancelState() {
        return Lists.newArrayList(WAIT.name(), FAILED.name());
    }

}
