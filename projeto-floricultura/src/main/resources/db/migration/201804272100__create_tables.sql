-- Table: cliente

-- DROP TABLE cliente;

CREATE TABLE cliente
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  cep character varying(10) NOT NULL,
  cidade character varying(50) NOT NULL,
  data_nascimento date NOT NULL,
  email character varying(255) NOT NULL,
  estado character varying(50) NOT NULL,
  nome character varying(50) NOT NULL,
  pais character varying(50) NOT NULL,
  rua character varying(60) NOT NULL,
  sexo integer NOT NULL,
  tipo_pessoa integer NOT NULL,
  CONSTRAINT cliente_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cliente
  OWNER TO floricultura;

-- Table: auditing.cliente_audited

-- DROP TABLE auditing.cliente_audited;

CREATE TABLE auditing.cliente_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  cep character varying(10),
  cidade character varying(50),
  data_nascimento date,
  email character varying(255),
  estado character varying(50),
  nome character varying(50),
  pais character varying(50),
  rua character varying(60),
  sexo integer,
  tipo_pessoa integer,
  CONSTRAINT cliente_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fkima7den5nqu6gcf78ivijr6t FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.cliente_audited
  OWNER TO floricultura;

  -- ----------------------------------------------------------
 -- Table: cor

-- DROP TABLE cor;

CREATE TABLE cor
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  descricao character varying(255) NOT NULL,
  CONSTRAINT cor_pkey PRIMARY KEY (id),
  CONSTRAINT uk_71piudk1pq5ekrseioieem1g5 UNIQUE (descricao)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE cor
  OWNER TO floricultura;

-- Table: auditing.cor_audited

-- DROP TABLE auditing.cor_audited;

CREATE TABLE auditing.cor_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  descricao character varying(255),
  CONSTRAINT cor_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fkls0yneqpkr2g77apaeonwv8jg FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.cor_audited
  OWNER TO floricultura;


  -- ----------------------------------------------
  
-- Table: tipo_familiar

-- DROP TABLE tipo_familiar;

CREATE TABLE tipo_familiar
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  nome_cientifico character varying(50) NOT NULL,
  CONSTRAINT tipo_familiar_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE tipo_familiar
  OWNER TO floricultura;


-- Table: auditing.tipo_familiar_audited

-- DROP TABLE auditing.tipo_familiar_audited;

CREATE TABLE auditing.tipo_familiar_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  nome_cientifico character varying(50),
  CONSTRAINT tipo_familiar_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fkd01bw7w3m8dm0owb74qcmy741 FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.tipo_familiar_audited
  OWNER TO floricultura;


-- -----------------------------------------------------------
  
-- Table: vendedor

-- DROP TABLE vendedor;

CREATE TABLE vendedor
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  codigo bigint NOT NULL,
  login character varying(255) NOT NULL,
  nome character varying(50) NOT NULL,
  senha character varying(255) NOT NULL,
  CONSTRAINT vendedor_pkey PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE vendedor
  OWNER TO floricultura;

-- Table: auditing.vendedor_audited

-- DROP TABLE auditing.vendedor_audited;

CREATE TABLE auditing.vendedor_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  codigo bigint,
  login character varying(255),
  nome character varying(50),
  senha character varying(255),
  CONSTRAINT vendedor_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fktj87nuheyqi0jf8p4x0bvo3ec FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.vendedor_audited
  OWNER TO floricultura;


-- ----------------------------------------------------
  
--- Table: venda

-- DROP TABLE venda;

CREATE TABLE venda
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  data date NOT NULL,
  entrega_status integer NOT NULL,
  natureza_venda integer NOT NULL,
  valor numeric(19,2) NOT NULL,
  valor_pago numeric(19,2) NOT NULL,
  valor_total numeric(19,2) NOT NULL,
  vendedor_id bigint NOT NULL,
  CONSTRAINT venda_pkey PRIMARY KEY (id),
  CONSTRAINT fkg2elbfxxrfufnbt699lao742h FOREIGN KEY (vendedor_id)
      REFERENCES vendedor (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE venda
  OWNER TO floricultura;

-- Table: auditing.venda_audited

-- DROP TABLE auditing.venda_audited;

CREATE TABLE auditing.venda_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  data date,
  entrega_status integer,
  natureza_venda integer,
  valor numeric(19,2),
  valor_pago numeric(19,2),
  valor_total numeric(19,2),
  vendedor_id bigint,
  CONSTRAINT venda_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk26tv3p3mhimda69rgm270r493 FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.venda_audited
  OWNER TO floricultura;


-- -----------------------------------------------------------
  
-- Table: produto

-- DROP TABLE produto;

CREATE TABLE produto
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  nome character varying(30) NOT NULL,
  tempo_vida integer NOT NULL,
  valor numeric(19,2) NOT NULL,
  cor_id bigint NOT NULL,
  familiar_id bigint NOT NULL,
  CONSTRAINT produto_pkey PRIMARY KEY (id),
  CONSTRAINT fk6meoj4aaywchbrreafbdbuoum FOREIGN KEY (cor_id)
      REFERENCES cor (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkb7thwy1h1p4qntd7wcdcsyqbx FOREIGN KEY (familiar_id)
      REFERENCES tipo_familiar (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT uk_hdot1xprktyi4sf2onvllkmkd UNIQUE (nome)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE produto
  OWNER TO floricultura;

-- Table: auditing.produto_audited

-- DROP TABLE auditing.produto_audited;

CREATE TABLE auditing.produto_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  nome character varying(30),
  tempo_vida integer,
  valor numeric(19,2),
  cor_id bigint,
  familiar_id bigint,
  CONSTRAINT produto_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk2l5bb6g514wbh2pi0sgodtexa FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.produto_audited
  OWNER TO floricultura;


-- -------------------------------------------------
  
-- Table: pedido

-- DROP TABLE pedido;

CREATE TABLE pedido
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  data_pedido date NOT NULL,
  preco_total numeric(19,2) NOT NULL,
  total_itens double precision NOT NULL,
  cliente_id bigint NOT NULL,
  CONSTRAINT pedido_pkey PRIMARY KEY (id),
  CONSTRAINT fk30s8j2ktpay6of18lbyqn3632 FOREIGN KEY (cliente_id)
      REFERENCES cliente (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE pedido
  OWNER TO floricultura;

-- Table: auditing.pedido_audited

-- DROP TABLE auditing.pedido_audited;

CREATE TABLE auditing.pedido_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  data_pedido date,
  preco_total numeric(19,2),
  total_itens double precision,
  cliente_id bigint,
  CONSTRAINT pedido_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk28672ddgved2eu0tr7wj7p88i FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.pedido_audited
  OWNER TO floricultura;


-- -----------------------------------------------------------
  
-- Table: item_venda

-- DROP TABLE item_venda;

CREATE TABLE item_venda
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  preco_unitario numeric(19,2) NOT NULL,
  quantidade integer NOT NULL,
  produto_id bigint NOT NULL,
  venda_id bigint,
  CONSTRAINT item_venda_pkey PRIMARY KEY (id),
  CONSTRAINT fk7wkinkvno0wlhv821hhu34y04 FOREIGN KEY (produto_id)
      REFERENCES produto (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fkkiky88fkai72328rhw3r3yebx FOREIGN KEY (venda_id)
      REFERENCES venda (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE item_venda
  OWNER TO floricultura;


-- Table: auditing.item_venda_audited

-- DROP TABLE auditing.item_venda_audited;

CREATE TABLE auditing.item_venda_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  preco_unitario numeric(19,2),
  quantidade integer,
  produto_id bigint,
  venda_id bigint,
  CONSTRAINT item_venda_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fkbqe6l5nhnk8hth28nim16ot0j FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.item_venda_audited
  OWNER TO floricultura;


-- -------------------------------------------------------------
  
-- Table: item_pedido

-- DROP TABLE item_pedido;

CREATE TABLE item_pedido
(
  id bigserial NOT NULL,
  created timestamp without time zone NOT NULL,
  updated timestamp without time zone,
  preco_unitario numeric(19,2) NOT NULL,
  quantidade integer NOT NULL,
  pedido_id bigint NOT NULL,
  produto_id bigint NOT NULL,
  CONSTRAINT item_pedido_pkey PRIMARY KEY (id),
  CONSTRAINT fk60ym08cfoysa17wrn1swyiuda FOREIGN KEY (pedido_id)
      REFERENCES pedido (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fktk55mn6d6bvl5h0no5uagi3sf FOREIGN KEY (produto_id)
      REFERENCES produto (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE item_pedido
  OWNER TO floricultura;


-- Table: auditing.item_pedido_audited

-- DROP TABLE auditing.item_pedido_audited;

CREATE TABLE auditing.item_pedido_audited
(
  id bigint NOT NULL,
  revision bigint NOT NULL,
  revision_type smallint,
  preco_unitario numeric(19,2),
  quantidade integer,
  pedido_id bigint,
  produto_id bigint,
  CONSTRAINT item_pedido_audited_pkey PRIMARY KEY (id, revision),
  CONSTRAINT fk50np3u01to2tpnew2cw5u2dd1 FOREIGN KEY (revision)
      REFERENCES auditing.revision (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
WITH (
  OIDS=FALSE
);
ALTER TABLE auditing.item_pedido_audited
  OWNER TO floricultura;

-- ----------------------------------------------------------------
  