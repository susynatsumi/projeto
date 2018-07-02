package br.com.eits.boot.domain.entity.floricultura.produto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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
@EqualsAndHashCode(callSuper = false)
@DataTransferObject
public class Cor extends AbstractEntity
{

	/**
	 * A cor deve ser única
	 */
	private static final long serialVersionUID = 1L;
	@NotEmpty
	@Column(nullable = false, unique = true)
	private String descricao;
}
