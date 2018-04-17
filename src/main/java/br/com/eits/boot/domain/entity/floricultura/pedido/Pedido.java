package br.com.eits.boot.domain.entity.floricultura.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;
import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@Audited
@EqualsAndHashCode(callSuper = true)
@DataTransferObject
public class Pedido extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6872025176485866356L;

	/**
	 * 
	 */
	
	@NotNull
	@Column(nullable = false)
	private Double totalItens;
	
	@NotNull
	@Column(nullable = false)
	private LocalDate dataPedido;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal precoTotal;

	@OneToMany(
			targetEntity = ItemPedido.class,
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "pedido"
			
	)
	private List<ItemPedido> itemPedido;
	
	@ManyToOne(
			targetEntity = Cliente.class,
			fetch = FetchType.LAZY,
			optional = false
	)
	private Cliente cliente;
	//TODO mapear relacionamento com cliente
	//TODO tornar o itemPedido lista
	//TODO utilizar cascade
	
	
}
