package br.com.eits.boot.domain.entity.floricultura.produto;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@Audited
@EqualsAndHashCode(callSuper = true)
@DataTransferObject
public class Produto extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 575811807508174420L;

	
	@NotEmpty
	@Column(nullable = false, length = 30, unique = true)
	private String nome;
	
	@NotNull
	@Column(nullable = false)
	private Integer tempoVida;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal valor;
	
	@NotNull
	@ManyToOne(
			fetch = FetchType.LAZY, targetEntity = Cor.class,
			optional = false
	)
	private Cor cor;
	
	@NotNull
	@ManyToOne(
			fetch = FetchType.LAZY, targetEntity = TipoFamiliar.class,
			optional = false
	)
	private TipoFamiliar familiar;
	
	//TODO lembrar de por o pedido conversar com prof

//	@ManyToOne(
//			fetch = FetchType.LAZY, 
//			targetEntity = Pedido.class,
//			
//	)
//	private Pedido pedido;
}
