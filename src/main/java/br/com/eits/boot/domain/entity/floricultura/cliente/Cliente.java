package br.com.eits.boot.domain.entity.floricultura.cliente;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
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
public class Cliente extends AbstractEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nome;
	
	@Email
	@NotNull
	@Column(nullable = false)
	private String email;
	
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String pais;
	
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String estado;
	
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String cidade;
	
	@NotEmpty
	@Column(nullable = false, length = 10)
	private String cep;
	
	@NotEmpty
	@Column(nullable = false, length = 60)
	private String rua;
	
	@NotNull
	@Column(nullable = false)
	private LocalDate dataNascimento;
	
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoPessoa tipoPessoa;
	
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;

	
}
