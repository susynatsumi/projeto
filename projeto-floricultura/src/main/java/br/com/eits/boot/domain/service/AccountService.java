package br.com.eits.boot.domain.service;

import static br.com.eits.common.application.i18n.MessageSourceHolder.translate;

import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

import br.com.eits.boot.application.configuration.settings.AppSettings;
import br.com.eits.boot.domain.repository.IAccountMailRepository;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.account.User;
import br.com.eits.boot.domain.entity.account.UserRole;
import br.com.eits.boot.domain.repository.account.IUserRepository;
import br.com.eits.common.application.i18n.MessageSourceHolder;

/**
 * @author rodrigo@eits.com.br
 * 
 * service diz que é uma provedora de servico
 * 
 * remoteProxy dwr para trafegar os objetos entre os serviços da serviçe
 * 
 */
@Service
@RemoteProxy
@Transactional
public class AccountService
{
	/*-------------------------------------------------------------------
	 *				 		     ATTRIBUTES
	 *-------------------------------------------------------------------*/
	/**
	 * Password encoder
	 */
	@Autowired
	private PasswordEncoder passwordEncoder;

	//Repositories
	/**
	 *
	 */
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IAccountMailRepository accountMailRepository;

	@Autowired
	private AppSettings appSettings;

	/*-------------------------------------------------------------------
	 *				 		     SERVICES
	 *-------------------------------------------------------------------*/

	/**
	 * @param user
	 * @return
	 */
	@PreAuthorize("hasAnyAuthority('" + UserRole.ADMINISTRATOR_VALUE + "','" + UserRole.MANAGER_VALUE + "')")
	public User insertUser( User user )
	{
		user.setDisabled( false );
		user.setPassword( this.passwordEncoder.encode( user.getPassword() ) );

		user = this.userRepository.save( user );
		this.accountMailRepository.sendNewUserAccount( user );
		return user;
	}

	/**
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public User findUserById( long id )
	{
		return this.userRepository.findById( id )
				.orElseThrow( () -> new IllegalArgumentException( translate( "repository.notFoundById", id ) ) );
	}

	/**
	 * @param pageable
	 * @param filter
	 * @return
	 */
	@Transactional(readOnly = true)
	public Page<User> listUsersByFilters( String filter, PageRequest pageable )
	{
		return this.userRepository.listByFilters( filter, pageable );
	}

	/**
	 *
	 */
	public void sendPasswordResetToken( String email )
	{
		User user = this.userRepository.findByEmail( email )
				.orElseThrow( () -> new IllegalArgumentException( translate( "userService.userNotFoundByEmail", email ) ) );
		user.setPasswordResetToken( UUID.randomUUID().toString() );
		user.setPasswordResetTokenExpiration( OffsetDateTime.now().plusHours( appSettings.getPasswordTokenExpirationHours() ) );
		user = this.userRepository.save( user );
		this.accountMailRepository.sendPasswordReset( user );
	}

	/**
	 *
	 */
	public void setUserPasswordByToken( String token, String newPassword )
	{
		User user = this.userRepository.findByPasswordResetTokenAndPasswordResetTokenExpirationAfter( token, OffsetDateTime.now() )
				.orElseThrow( () -> new IllegalArgumentException( translate( "userService.passwordResetTokenInvalid" ) ) );
		user.setPasswordResetToken( null );
		user.setPasswordResetTokenExpiration( null );
		user.setPassword( this.passwordEncoder.encode( newPassword ) );
		user = this.userRepository.save( user );
		this.accountMailRepository.sendPasswordResetNotice( user );
	}
}