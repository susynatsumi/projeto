SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET default_with_oids = false;

SET search_path TO public;

TRUNCATE "pedido" CASCADE;

INSERT INTO "pedido" (idPedido, created, disabled, totalItens, dataPedido, precoTotal, name, role) 
     VALUES ( 1000, NOW(), false, 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 'Administrator', 0);
     
INSERT INTO "pedido" (idPedido, created, disabled, totalItens, dataPedido, precoTotal, name, role) 
     VALUES ( 1001, NOW(), false, 'user001@testing.com', 'd1bd2f08fead38a982aed9d4ca060152400b1b8f', 0);
     
