SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "vendedor" CASCADE;

INSERT INTO "vendedor" (nome, created, disabled, email, password, role) 
     VALUES ( "Vendedor1", NOW(), false, 'admin@email.com', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 0);