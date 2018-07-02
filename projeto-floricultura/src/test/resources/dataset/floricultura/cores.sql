SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false; 

SET search_path TO public;

TRUNCATE "cor" CASCADE;

INSERT INTO cor(
            id, created, updated, descricao)
    VALUES (1011, NOW(), NULL, 'Vermelho');
    
INSERT INTO cor(
            id, created, updated, descricao)
    VALUES (1012, NOW(), NULL, 'Roxo');
    
INSERT INTO cor(
            id, created, updated, descricao)
    VALUES (1013, NOW(), NULL, 'Branco');