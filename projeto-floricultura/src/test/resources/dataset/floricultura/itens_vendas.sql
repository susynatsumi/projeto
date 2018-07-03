SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "tipo_familiar" CASCADE;
TRUNCATE "cor" CASCADE;
TRUNCATE "produto" CASCADE;
TRUNCATE "venda" CASCADE;
TRUNCATE "vendedor" CASCADE;
TRUNCATE "item_venda" CASCADE;

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

-- Insere os produtos ----------------
INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2004, NOW(), NULL, 'Margarida', 2, 10.00, 1011, 1);
    
INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2000, NOW(), NULL, 'Rosa', 1, 20.00, 1012, 2);  

-- -------------fim produtos ----------------------

    -- --------------vendedor 
   
INSERT INTO vendedor(
            id, created, updated, codigo, login, nome, senha)
    VALUES (1000, NOW(), NULL, 198758, 'vendedor1', 'Joao', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f');
    
INSERT INTO vendedor(
            id, created, updated, codigo, login, nome, senha)
    VALUES (1001, NOW(), NULL, 198758, 'vendedor1', 'Joao', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f');
       
-- ---------- fim insercao vendedor

    
-----------------Insere vendas ------------------------------------------------------------------ 
    
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
    		
-- ---------------- fim insercao venda ------
    		
-- fim dependencias ---------------

    		
-- ---------------- insercao de itens na venda -----

INSERT INTO item_venda(
    id, created, updated, preco_unitario, quantidade, venda_id, produto_id) 
VALUES (130, NOW(), NULL, 50.00, 5, 200, 2004);

INSERT INTO item_venda(
    id, created, updated, preco_unitario, quantidade, venda_id, produto_id) 
VALUES (131, NOW(), NULL, 70.00, 5, 200, 2004);


-- ---------------- insercao de itens na venda -----