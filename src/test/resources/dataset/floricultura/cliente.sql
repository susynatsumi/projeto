SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE cliente CASCADE;

INSERT INTO cliente(
            id, created, updated, cep, cidade, data_nascimento, email, estado, 
            nome, pais, rua, sexo, tipo_pessoa)
    VALUES (1000, NOW(), NULL, '85877000', 'Missal', NOW(), 'paranaue@email.com', 'Parana', 
            'Cabral', 'Brasil', 'Jaozino', 0, 1);
            
INSERT INTO cliente(
            id, created, updated, cep, cidade, data_nascimento, email, estado, 
            nome, pais, rua, sexo, tipo_pessoa)
    VALUES (1001, NOW(), NULL, '8588000', 'Missaleiro', NOW(), 'paranaue2@email.com', 'Parana2', 
            'Cabrale', 'Brasil', 'Jaozino', 0, 1);
