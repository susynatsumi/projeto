package br.com.eits.boot.domain.entity.floricultura.vendedor;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotEmpty;

import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@Table
@Audited
@EqualsAndHashCode(callSuper = true)
@DataTransferObject
public class Vendedor extends AbstractEntity {

	/**
	 * A anotacao PrimaryKeyJoinColumn é usada para unir a tabela primária de uma subclasse 
	 * de entidade na estratégia JOINED de mapeamento à tabela primária de sua superclasse;
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
			fetch = FetchType.LAZY, 
			targetEntity = Venda.class,
			cascade = CascadeType.ALL,
			mappedBy = "vendedor",
			orphanRemoval = true
	)
	private List<Venda> vendas;

}
