package br.com.eits.boot.domain.entity.account;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.directwebremoting.annotations.DataTransferObject;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.eits.common.domain.entity.AbstractEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *entity define a tabela na base 
 *table especifica quem é a tabela primaria da entidade, quando há herança
 *
 *excluir do hascode o exclude para o caso de se ter uma lista
 *
 *audited é do envers para dizer que ela é passivel de auditoria
 *todos os atributos são passíveis de auditoria
 *sendo que cria uma tabela user_audited, sendo quem alterou e tudo mais
 *
 *Data transfer object, faz com que esta classe seja acessível no angular
 *
 *ver dwr
 *
 *orphanRemoval  = true em caso de ter um objeto com a lista carregada 
 *e se excluir um item desta lista no banco, se tiver true
 *a lista será atualizada, senão não.
 *
 *
 *
 */
@Data
@Entity
@Audited
@Table
@EqualsAndHashCode(callSuper=true)
@DataTransferObject
public class User extends AbstractEntity implements Serializable, UserDetails
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -4052986759552589018L;

	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	// Basic
	/**
	 * 
	 */
	@NotEmpty
	@Column(nullable = false, length = 50)
	private String name;

	/**
	 * 
	 */
	@Email
	@NotNull
	@Column(nullable = false, length = 144, unique = true)
	private String email;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	private Boolean disabled;

	/**
	 * diz que quando acessado via rede, não pode ser alterado
	 */
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank
	@Column(nullable = false, length = 100)
	private String password;

	/**
	 * 
	 */
	@NotNull
	@Column(nullable = false)
	@Enumerated(EnumType.ORDINAL)
	private UserRole role;

	/**
	 * 
	 */
	private OffsetDateTime lastLogin;

	/**
	 *
	 */
	private String passwordResetToken;

	/**
	 *
	 */
	private OffsetDateTime passwordResetTokenExpiration;

	/*-------------------------------------------------------------------
	 * 		 					CONSTRUCTORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	public User()
	{
	}

	/**
	 * 
	 * @param id
	 */
	public User( Long id )
	{
		super( id );
	}
	
	/**
	 * 
	 * @param id
	 * @param name
	 * @param email
	 * @param enabled
	 * @param role
	 * @param password
	 */
	public User( Long id, String name, String email, Boolean disabled, UserRole role, String password )
	{
		super( id );
		this.email = email;
		this.name = name;
		this.disabled = disabled;
		this.password = password;
		this.role = role;
	}

	/*-------------------------------------------------------------------
	 *							BEHAVIORS
	 *-------------------------------------------------------------------*/
	/**
	 * 
	 */
	@Override
	@Transient
	public Set<GrantedAuthority> getAuthorities()
	{
		final Set<GrantedAuthority> authorities = new HashSet<>();

		if ( this.role == null )
		{
			return null;
		}
		
		authorities.addAll( this.role.getAuthorities() );

		return authorities;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired()
	{
		return true;
	}

	/**
	 * 
	 */
	@Override
	@Transient
	public boolean isEnabled()
	{
		return !this.disabled;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	@Transient
	public String getPassword()
	{
		return this.password;
	}

	/*
	 * (non-Javadoc)
	 * @see
	 * org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	@Transient
	public String getUsername()
	{
		return this.email;
	}
	
	/*-------------------------------------------------------------------
	 *						GETTERS AND SETTERS
	 *-------------------------------------------------------------------*/
}