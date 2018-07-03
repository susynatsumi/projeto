SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "venda" CASCADE;
TRUNCATE "vendedor" CASCADE;

INSERT INTO vendedor(
            id, created, updated, codigo, login, nome, senha)
    VALUES (1000, NOW(), NULL, 198758, 'vendedor1', 'Joao', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f');
    
INSERT INTO vendedor(
            id, created, updated, codigo, login, nome, senha)
    VALUES (1001, NOW(), NULL, 198758, 'vendedor1', 'Joao', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f');
       
    
INSERT INTO venda(
            id, created, updated, data, entrega_status, natureza_venda, valor, 
            valor_pago, valor_total, vendedor_id)
            
    VALUES (200, NOW(), NULL, NOW(), 1, 0, 10, 
    		20.00, 10.00, 1000);
    
INSERT INTO venda(
            id, created, updated, data, entrega_status, natureza_venda, valor, 
            valor_pago, valor_total, vendedor_id)
            
    VALUES (201, NOW(), NULL, NOW(), 0, 1, 10, 
    		20.00, 10.00, 1001);