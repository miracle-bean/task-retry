create table task
(
    id              bigint auto_increment comment '主键'
        primary key,
    create_time     timestamp   default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time     timestamp   default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    business_type   varchar(64)                           not null comment '任务类型，业务自定义',
    business_id     varchar(64)                           null comment '业务自定义id',
    payload         text                                  not null comment '任务装配器',
    state           varchar(32) default 'WAIT'            not null comment '任务状态：WAIT、RUNNING、FINISHED、FAILED、CANCEL',
    max_retry_count int(4)      default 3                 not null comment '最大重试次数',
    executed_count  int(4)      default 0                 not null comment '已经执行的次数',
    error_message   text                                  null comment '执行过程中的报错信息',
    `order`         int(4)      default 99                not null comment '执行优先级，越大优先级越高',
    `async`         boolean     default true              not null comment '异步或同步， true：异步，false：同步',
    version         int(4)      default 0                 not null comment '乐观锁'
)
    comment '任务重试表' collate = utf8mb4_gunicode_ci;
