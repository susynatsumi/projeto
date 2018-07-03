package br.com.eits.boot.domain.entity.floricultura.venda;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@Audited
@EqualsAndHashCode(callSuper = false)
@DataTransferObject
public class ItemVenda extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	@NotNull
	@Column(nullable = false)
	private Integer quantidade;
	
	@ManyToOne(
		fetch = FetchType.LAZY,
		targetEntity = Venda.class,
		optional = false
	)
	private Venda venda;
	
	@ManyToOne(
		fetch = FetchType.LAZY,
		targetEntity = Produto.class,
		optional = false
	)
	private Produto produto;
	
	

}
