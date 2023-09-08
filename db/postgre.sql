CREATE TABLE task
(
    id              bigserial PRIMARY KEY,
    create_time     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time     timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
    business_type   varchar(64) COLLATE pg_catalog."default" NOT NULL,
    business_id     varchar(64) COLLATE pg_catalog."default",
    payload         text COLLATE pg_catalog."default" NOT NULL,
    state           varchar(32) COLLATE pg_catalog."default" NOT NULL DEFAULT 'WAIT',
    max_retry_count int NOT NULL DEFAULT '3',
    executed_count  int NOT NULL DEFAULT '0',
    error_message   text COLLATE pg_catalog."default",
    "order"         int NOT NULL DEFAULT '99',
    async           boolean NOT NULL DEFAULT 'true',
    version         int NOT NULL DEFAULT '0'
);