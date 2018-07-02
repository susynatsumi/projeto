package br.com.eits.boot.test.domain.service.floricultura.service.produto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.produto.TipoFamiliar;
import br.com.eits.boot.domain.repository.floricultura.produto.ITipoFamiliarRepository;
import br.com.eits.boot.domain.service.floricultura.produto.TipoFamiliarService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class TipoFamiliarServiceIntegrationTests extends AbstractIntegrationTests{

	@Autowired
	private TipoFamiliarService familiarService;
	
	@Autowired
	private ITipoFamiliarRepository tipoFamiliarRepository;
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/tipos_familiares.sql"
	})
	public void insertNomeCientificoMustPass() {
		
		final TipoFamiliar familiar = new TipoFamiliar();
		familiar.setNomeCientifico("Bromeliaceae");
		this.familiarService.insertNomeCientifico(familiar);
		Assert.assertNotNull(familiar);
		Assert.assertNotNull(familiar.getNomeCientifico());
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/tipos_familiares.sql"
	})
	public void updateNomeCientificoMustPass() {
		
		final TipoFamiliar familiar = tipoFamiliarRepository.findById(1L).orElse(null);
		familiar.setNomeCientifico("Tillandsia");
		
		this.familiarService.updateNomeCientifico(familiar);
		
		final TipoFamiliar familiarBusca = tipoFamiliarRepository.findById(1L).orElse(null);
		Assert.assertNotNull(familiarBusca);
		Assert.assertNotNull("Tillandsia", familiarBusca.getNomeCientifico());
	}
	
	/**
	 * Falha ao dar update por causa do id ser nulo
	 */
	@Test(expected = IllegalArgumentException.class)
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/tipos_familiares.sql"
	})
	public void updateNomeCientificoMustFail() {
		
		final TipoFamiliar familiar = new TipoFamiliar();
		familiar.setNomeCientifico("Tillandsia");
		
		this.familiarService.updateNomeCientifico(familiar);
		
		final TipoFamiliar familiarBusca = tipoFamiliarRepository.findById(1L).orElse(null);
		Assert.assertNotNull(familiarBusca);
		Assert.assertNotNull("Tillandsia", familiarBusca.getNomeCientifico());
	}
	
	/**
	 * Listar por filtros um elemento
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/tipos_familiares.sql" })
	public void listCoresMustPassUmDescricao() {

		final Page<TipoFamiliar> familiares = this.familiarService.listNomeCientificoByFilters("Leucanthemum vulgare", null);

		Assert.assertNotNull(familiares);
		Assert.assertEquals(1L, familiares.getTotalElements());

	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/tipos_familiares.sql" })
	public void removeTipoFamiliarMustPass() {
		
		this.familiarService.removeNomeCientifico(1L);

		final TipoFamiliar familiar = tipoFamiliarRepository.findById(1L).orElse(null);
		Assert.assertNotNull(familiar);
	}
}
