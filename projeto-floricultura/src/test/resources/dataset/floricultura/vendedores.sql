SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE vendedor CASCADE;
     
INSERT INTO vendedor(
            id, created, updated, codigo, login, nome, senha)
    VALUES (1, NOW(), NULL, 198758, 'vendedor1', 'Joao', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f');
    