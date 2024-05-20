-- miracle.task definition

CREATE TABLE `task`
(
    `id`              bigint(20)                             NOT NULL AUTO_INCREMENT COMMENT '主键',
    `create_time`     timestamp                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     timestamp                              NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `business_type`   varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '任务类型，业务自定义',
    `business_id`     varchar(64) COLLATE utf8mb4_unicode_ci          DEFAULT NULL COMMENT '业务自定义id',
    `payload`         text COLLATE utf8mb4_unicode_ci        NOT NULL COMMENT '任务装配器',
    `state`           varchar(32) COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'WAIT' COMMENT '任务状态：WAIT、RUNNING、FINISHED、FAILED、CANCEL',
    `max_retry_count` int(4)                                 NOT NULL DEFAULT '3' COMMENT '最大重试次数',
    `executed_count`  int(4)                                 NOT NULL DEFAULT '0' COMMENT '已经执行的次数',
    `next_fire_time`  timestamp                              NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '下次执行时间',
    `error_message`   varchar(1024) COLLATE utf8mb4_unicode_ci        DEFAULT NULL COMMENT '执行过程中的报错信息',
    `task_order`      int(4)                                 NOT NULL DEFAULT '99' COMMENT '执行优先级，越大优先级越高',
    `async`           tinyint(1)                             NOT NULL DEFAULT '1' COMMENT '异步或同步， true：异步，false：同步',
    `version`         int(4)                                 NOT NULL DEFAULT '0' COMMENT '乐观锁',
    PRIMARY KEY (`id`),
    KEY `task_state_IDX` (`state`, `business_type`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 49
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT ='任务重试表';