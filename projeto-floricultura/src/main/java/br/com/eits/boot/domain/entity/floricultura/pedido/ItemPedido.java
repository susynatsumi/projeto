package br.com.eits.boot.domain.entity.floricultura.pedido;

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
public class ItemPedido extends AbstractEntity{
	
	/*-------------------------------------------------------------------
	 *				 		     Atributos do ItemPedido
	 *-------------------------------------------------------------------*/

	private static final long serialVersionUID = 21L;

	/**
	 * Preco unitario do item
	 */
	@NotNull
	@Column(nullable = false)
	private BigDecimal precoUnitario;
	
	/**
	 * Quantidade
	 */
	@NotNull
	@Column(nullable = false)
	private Integer quantidade;
	
	/**
	 * Mapea a relação com o pedido
	 */
	@ManyToOne(
			fetch = FetchType.LAZY,
			optional = false,
			targetEntity = Pedido.class
	)
	private Pedido pedido;
	
	/**
	 * Mapeando relacionamento com o produto
	 */
	@ManyToOne(
			fetch = FetchType.LAZY,
			optional = false,
			targetEntity = Produto.class
	)
	private Produto produto;

}
