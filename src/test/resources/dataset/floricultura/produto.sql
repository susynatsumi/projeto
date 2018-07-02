SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "produto" CASCADE;

INSERT INTO "produto" (id, created, disabled, nome, tempo_vida, valor, role) 
     VALUES (2004, NOW(), false, 'admin@email.com', '04/04/2019', NOW(), 0);
     