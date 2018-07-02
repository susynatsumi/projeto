package br.com.eits.boot.domain.entity.floricultura.venda;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type = "enum")
public enum NaturezaVenda {

	VENDA,
	PEDIDO;
}
