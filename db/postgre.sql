-- public.task definition

-- Drop table

-- DROP TABLE public.task;

CREATE TABLE public.task
(
    id              bigserial   NOT NULL,
    create_time     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_time     timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    business_type   varchar(64) NOT NULL,
    business_id     varchar(64) NULL,
    payload         text        NOT NULL,
    state           varchar(32) NOT NULL DEFAULT 'WAIT':: character varying,
    max_retry_count int4        NOT NULL DEFAULT 3,
    executed_count  int4        NOT NULL DEFAULT 0,
    next_fire_time  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    error_message   text NULL,
    task_order      int4        NOT NULL DEFAULT 99,
    async           bool        NOT NULL DEFAULT false,
    "version"       int4        NOT NULL DEFAULT 0,
    CONSTRAINT task_pkey PRIMARY KEY (id)
);
CREATE INDEX task_state_idx ON public.task USING btree (state, business_type);