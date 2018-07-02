SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "venda" CASCADE;

INSERT INTO "venda" (data, created, disabled, valorTotal, valorPago, role) 
     VALUES ( NOW(), NOW(), false, 10.00, 20.00, 'Administrator', 0);