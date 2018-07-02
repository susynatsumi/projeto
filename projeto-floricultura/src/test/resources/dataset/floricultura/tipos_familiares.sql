SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "tipo_familiar" CASCADE;

INSERT INTO tipo_familiar(
            id, created, updated, nome_cientifico)
	VALUES (1, NOW(), NULL, 'Leucanthemum vulgare');

INSERT INTO tipo_familiar(
            id, created, updated, nome_cientifico)
	VALUES (2, NOW(), NULL, 'Lilium');