package br.com.eits.boot.domain.entity.floricultura.venda;

import org.directwebremoting.annotations.DataTransferObject;

@DataTransferObject(type = "enum")
public enum EntregaStatus {

	EFETUADO,
	ESPERA;
}
