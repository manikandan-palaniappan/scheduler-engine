CREATE TABLE log
(
  id bigint NOT NULL,
  description character varying(255),
  reference_id bigint,
  resource_type integer,
  version integer,
  CONSTRAINT log_pkey PRIMARY KEY (id)
)