CREATE TABLE public.reportgpt (
    id bigint NOT NULL,
    description text NOT NULL,
    template_id bigint,
    topic_id bigint,
    abnt boolean
);


ALTER TABLE public.reportgpt OWNER TO hackathon;

CREATE SEQUENCE public.reportgpt_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.reportgpt_id_seq OWNER TO hackathon;

ALTER SEQUENCE public.reportgpt_id_seq OWNED BY public.reportgpt.id;

CREATE TABLE public.template (
    id bigint NOT NULL,
    description text NOT NULL,
    title character varying(100) NOT NULL
);


ALTER TABLE public.template OWNER TO hackathon;


CREATE SEQUENCE public.template_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.template_id_seq OWNER TO hackathon;

ALTER SEQUENCE public.template_id_seq OWNED BY public.template.id;

CREATE TABLE public.topic (
    id bigint NOT NULL,
    description character varying(100) NOT NULL
);


ALTER TABLE public.topic OWNER TO hackathon;

CREATE SEQUENCE public.topic_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.topic_id_seq OWNER TO hackathon;

ALTER SEQUENCE public.topic_id_seq OWNED BY public.topic.id;

ALTER TABLE ONLY public.reportgpt ALTER COLUMN id SET DEFAULT nextval('public.reportgpt_id_seq'::regclass);

ALTER TABLE ONLY public.template ALTER COLUMN id SET DEFAULT nextval('public.template_id_seq'::regclass);

ALTER TABLE ONLY public.topic ALTER COLUMN id SET DEFAULT nextval('public.topic_id_seq'::regclass);

INSERT INTO public.template (id, description, title) VALUES (1, 'Faça um plano de negócio para consolidação de ideias sobre :topic', 'Plano de negócio para consolidação de ideias');
INSERT INTO public.template (id, description, title) VALUES (2, 'Faça um plano de negócio para estruturação e desenvolvimento de ideias sobre :topic', 'Plano de negócio para estruturação e desenvolvimento de ideias');
INSERT INTO public.template (id, description, title) VALUES (3, 'Faça um plano de negócio para investidores sobre :topic', 'Plano de negócio para investidores');

INSERT INTO public.topic (id, description) VALUES (1, 'venda de notebooks');
INSERT INTO public.topic (id, description) VALUES (2, 'venda de café');
INSERT INTO public.topic (id, description) VALUES (3, 'venda de peixe');
INSERT INTO public.topic (id, description) VALUES (4, 'montar escritório de advocacia');
INSERT INTO public.topic (id, description) VALUES (5, 'montar loja de roupas');
INSERT INTO public.topic (id, description) VALUES (6, 'montar parque aquático');

SELECT pg_catalog.setval('public.reportgpt_id_seq', 1, true);


SELECT pg_catalog.setval('public.template_id_seq', 3, true);


SELECT pg_catalog.setval('public.topic_id_seq', 6, true);

ALTER TABLE ONLY public.reportgpt
    ADD CONSTRAINT reportgpt_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.template
    ADD CONSTRAINT template_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.topic
    ADD CONSTRAINT topic_pkey PRIMARY KEY (id);


ALTER TABLE ONLY public.reportgpt
    ADD CONSTRAINT topic_template_uk UNIQUE (topic_id, template_id);

ALTER TABLE ONLY public.reportgpt
    ADD CONSTRAINT fk8recf5trmqjttbdkms037jtuw FOREIGN KEY (template_id) REFERENCES public.template(id);


ALTER TABLE ONLY public.reportgpt
    ADD CONSTRAINT fkmny05q5t8w1517sd4qekid0b1 FOREIGN KEY (topic_id) REFERENCES public.topic(id);


