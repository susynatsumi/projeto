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

	/*-------------------------------------------------------------------
	 *				 		     Atributos do cliente
	 *-------------------------------------------------------------------*/
	
	private static final long serialVersionUID = 1L;

	/**
	 * Nome do cliente
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nome;
	
	/**
	 * Email do cliente
	 */
	@Email
	@NotNull
	@Column(nullable = false)
	private String email;
	
	/**
	 * Pais de origem
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String pais;
	
	/**
	 * Estado
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String estado;
	
	/**
	 * Cidade
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String cidade;
	
	/**
	 * Cep 
	 */
	@NotEmpty
	@Column(nullable = false, length = 10)
	private String cep;
	
	/**
	 * Rua
	 */
	@NotEmpty
	@Column(nullable = false, length = 60)
	private String rua;
	
	/**
	 * LocalDate da data de nascimento respectivo ano, mes e dia
	 */
	@NotNull
	@Column(nullable = false)
	private LocalDate dataNascimento;
	
	/**
	 * Enum do tipo pessoa sendo física e jurídica
	 * Enumerated(EnumType.ORDINAL) valor descrito e lido do tipoPessoa no bd, sera na ordem de 0 e 1
	 * No caso Pessoa física é 1 e o Pessoa jurídica 2
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private TipoPessoa tipoPessoa;
	
	/**
	 * Enum do tipo pessoa sendo feminino e masculino
	 * Enumerated(EnumType.ORDINAL) valor descrito e lido do enum sexo no bd, sera na ordem de 0 e 1
	 * No caso feminino é 1 e o masculino 2
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private Sexo sexo;

	
}
