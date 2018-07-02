package br.com.eits.boot.domain.entity.floricultura.venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;

import br.com.eits.boot.domain.entity.floricultura.vendedor.Vendedor;
import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@Audited
@EqualsAndHashCode(callSuper = false)
@DataTransferObject
public class Venda extends AbstractEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(nullable = false)
	private LocalDate data;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal valor;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorTotal;
	
	@NotNull
	@Column(nullable = false)
	private BigDecimal valorPago;

	@Enumerated(EnumType.ORDINAL)
	@NotNull
	@Column(nullable = false)
	private NaturezaVenda naturezaVenda;
	
	@OneToMany(
			fetch = FetchType.LAZY,
			targetEntity = ItemVenda.class,
			cascade = javax.persistence.CascadeType.ALL, 
			mappedBy = "venda", 
			orphanRemoval = true
	)	
	private List<ItemVenda> itemVenda;
	//TODO uma lista e usar o cascade
	//TOOD fazer relacionamento com o vendedor
	
	@Enumerated(EnumType.ORDINAL)
	@NotNull
	@Column(nullable = false)
	private EntregaStatus entregaStatus;
	
	@ManyToOne(
			fetch = FetchType.LAZY,
			targetEntity = Vendedor.class,
			optional = false
	)
	private Vendedor vendedor;
}
