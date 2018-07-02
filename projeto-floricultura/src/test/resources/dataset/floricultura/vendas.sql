SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "venda" CASCADE;
   
INSERT INTO venda(
            id, created, updated, data, entrega_status, natureza_venda, valor, 
            valor_pago, valor_total, vendedor_id)
            
    VALUES (200, NOW(), NULL, NOW(), ESPERA, PEDIDO, 10, 
    		20.00, 10.00, 3);
    
INSERT INTO venda(
            id, created, updated, data, entrega_status, natureza_venda, valor, 
            valor_pago, valor_total, vendedor_id)
            
    VALUES (201, NOW(), NULL, NOW(), EFETUADO, VENDA, 10, 
    		20.00, 10.00, 3);