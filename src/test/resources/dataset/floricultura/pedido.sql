SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "pedido" CASCADE;

INSERT INTO pedido (
			id, created, updated, totalItens, dataPedido, precoTotal) 
     VALUES ( 1002, NOW(), NULL, 3, NOW(), 10.00);
     
     