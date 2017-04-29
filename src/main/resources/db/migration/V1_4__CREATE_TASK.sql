CREATE TABLE task
(
  id bigint NOT NULL,
  description character varying(255),
  duration integer NOT NULL,
  end_time timestamp without time zone,
  parent_task_id bigint,
  start_time timestamp without time zone,
  task_status integer,
  version integer,
  job_id bigint NOT NULL,
  CONSTRAINT task_pkey PRIMARY KEY (id),
  CONSTRAINT fk9hh4r25ufrrdjgqm37vgbojnj FOREIGN KEY (job_id)
      REFERENCES job (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)