SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "produto" CASCADE;
TRUNCATE "pedido" CASCADE;
TRUNCATE "item_pedido" CASCADE;
TRUNCATE "cliente" CASCADE;
TRUNCATE "tipo_familiar" CASCADE;
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

-- --------------------------------------------------------------------------------------

INSERT INTO tipo_familiar(
            id, created, updated, nome_cientifico)
	VALUES (1, NOW(), NULL, 'Leucanthemum vulgare');

INSERT INTO tipo_familiar(
            id, created, updated, nome_cientifico)
	VALUES (2, NOW(), NULL, 'Lilium');

-- --------------------------------------------------------------------------------------	
	
INSERT INTO cliente(
            id, created, updated, cep, cidade, data_nascimento, email, estado, 
            nome, pais, rua, sexo, tipo_pessoa)
    VALUES (1000, NOW(), NULL, '85877000', 'Missal', NOW(), 'paranaue@email.com', 'Parana', 
            'Cabral', 'Brasil', 'Jaozino', 1, 0);
            
INSERT INTO cliente(
            id, created, updated, cep, cidade, data_nascimento, email, estado, 
            nome, pais, rua, sexo, tipo_pessoa)
    VALUES (1001, NOW(), NULL, '5588000', 'Ottawa', NOW(), 'caedu@email.com', 'Toronto', 
            'Eduardo :(', 'Canada', 'Greely', 0, 1);

    
----------------------------------------------------------------------------------------


INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2004, NOW(), NULL, 'Margarida', 2, 10.00, 1011, 1);
    
INSERT INTO produto(
            id, created, updated, nome, tempo_vida, valor, cor_id, familiar_id)
    VALUES (2000, NOW(), NULL, 'Rosa', 1, 20.00, 1012, 2);  

 
-- --------------------------------------------------------------------------------------

INSERT INTO pedido(
            id, created, updated, data_pedido, preco_total, total_itens, cliente_id)
    VALUES (1001, NOW(), NULL, NOW(), 11.00, 1, 1000);
     
INSERT INTO pedido(
            id, created, updated, data_pedido, preco_total, total_itens, cliente_id)
    VALUES (1002, NOW(), NULL, NOW(), 12.00, 2, 1001);
    
-- --------------------------------------------------------------------------------------
    
    
INSERT INTO item_pedido(
            id, created, updated, preco_unitario, quantidade, pedido_id, produto_id)
    VALUES (289, NOW(), NULL, 10.00, 2, 1001, 2004);    