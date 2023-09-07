package com.task.retry.exception;

/**
 * Author: miracle
 * Date: 2023/9/7 14:21
 */
public class TaskRetryException extends RuntimeException {

    private static final long serialVersionUID = -7033857680827931305L;

    protected TaskRetryException(String message) {
        super(message);
    }

    protected TaskRetryException(Throwable cause) {
        super(cause);
    }

    protected TaskRetryException(String message, Throwable cause) {
        super(message, cause);
    }

    public static TaskRetryException error(String message) {
        return new TaskRetryException(message);
    }

    public static TaskRetryException error(Throwable cause) {
        return new TaskRetryException(cause);
    }

    public static TaskRetryException error(String message, Throwable cause) {
        return new TaskRetryException(message, cause);
    }

}
