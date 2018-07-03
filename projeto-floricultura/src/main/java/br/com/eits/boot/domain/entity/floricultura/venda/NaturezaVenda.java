package br.com.eits.boot.domain.entity.floricultura.venda;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type = "enum")
public enum NaturezaVenda {

	VENDA, // 0
	PEDIDO; // 1
}
