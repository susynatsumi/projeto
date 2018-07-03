SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "cor" CASCADE;
TRUNCATE "produto" CASCADE;
TRUNCATE "tipo_familiar" CASCADE;

-- insere dependencias------------------

-- inicio tipo familiar --------

INSERT INTO tipo_familiar(
            id, created, updated, nome_cientifico)
	VALUES (1, NOW(), NULL, 'Leucanthemum vulgare');

INSERT INTO tipo_familiar(
            id, created, updated, nome_cientifico)
	VALUES (2, NOW(), NULL, 'Lilium');

-- Fim tipo familiar --------

-- Inicio cores ---------------


INSERT INTO cor(
            id, created, updated, descricao)
    VALUES (1011, NOW(), NULL, 'Vermelho');
    
INSERT INTO cor(
            id, created, updated, descricao)
    VALUES (1012, NOW(), NULL, 'Roxo');
    
INSERT INTO cor(
            id, created, updated, descricao)
    VALUES (1013, NOW(), NULL, 'Branco');
    
-- Fim cores ---------------

-- fim dependencias ---------------

-- Insere os produtos ----------------
INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2004, NOW(), NULL, 'Margarida', 2, 10.00, 1011, 1);
    
INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2000, NOW(), NULL, 'Rosa', 1, 20.00, 1012, 2);  

-------------Fim ---------------------------------------------------------------------

    

