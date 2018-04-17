package br.com.eits.boot.domain.entity.floricultura.cliente;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type = "enum")
public enum TipoPessoa {
	
	PESSOA_FISICA,
	PESSOA_JURIDICA;
}
