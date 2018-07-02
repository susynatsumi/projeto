SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "item_pedido" CASCADE;

INSERT INTO item_pedido(
            id, created, updated, preco_unitario, quantidade, pedido_id, produto_id)
    VALUES (289, NOW(), NULL, 10.00, 2, 1001, 2004);
   
----------------------------------------------------------------------------------------

TRUNCATE "pedido" CASCADE;

INSERT INTO pedido(
            id, created, updated, data_pedido, preco_total, total_itens, cliente_id)
    VALUES (1001, NOW(), NULL, NOW(), 11.00, 1, 1000);
     
INSERT INTO pedido(
            id, created, updated, data_pedido, preco_total, total_itens, cliente_id)
    VALUES (1002, NOW(), NULL, NOW(), 12.00, 2, 1001);
    
----------------------------------------------------------------------------------------
    
TRUNCATE "produto" CASCADE;

INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2004, NOW(), NULL, 'Margarida', 2, 10.00, 1011, 1);
    
INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2000, NOW(), NULL, 'Rosa', 1, 20.00, 1012, 2);  
