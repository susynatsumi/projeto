package br.com.eits.boot.domain.entity.floricultura.vendedor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table
@Audited
@EqualsAndHashCode(callSuper = true)
@DataTransferObject
public class Vendedor extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nome;
	
	@NotEmpty
	@Column(nullable = false)
	private Long codigo;
	
	@NotEmpty
	@Column(nullable = false)
	private String login;
	
	@NotEmpty
	@Column(nullable = false)
	private String senha;
	
	@OneToMany(
			fetch = javax.persistence.FetchType.LAZY, 
			targetEntity = Venda.class,
			cascade = CascadeType.MERGE,
			mappedBy = "vendedor",
			orphanRemoval = true
	)
	private List<Venda> vendas;
	
	//TODO obs pesquisa do merge
}
