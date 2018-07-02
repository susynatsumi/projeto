package br.com.eits.boot.test.domain.service.floricultura.service.produto;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.TransactionSystemException;

import br.com.eits.boot.domain.entity.floricultura.produto.Cor;
import br.com.eits.boot.domain.repository.floricultura.produto.ICorRepository;
import br.com.eits.boot.domain.service.floricultura.produto.CorService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class CorServiceIntegrationTests extends AbstractIntegrationTests {

	@Autowired
	private CorService corService;

	@Autowired
	private ICorRepository corRepository;

	/*
	 * Insere cor
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void insertCorMustPass() {

		final Cor cor = new Cor();
		cor.setDescricao("Amarelo");
		this.corService.insertCor(cor);

		Assert.assertNotNull(cor);
		Assert.assertNotNull(cor.getId());
	}

	/**
	 * Verifica se existe um cor e falhar por ser unique
	 */
	@Test(expected = DataIntegrityViolationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void insertCorMustFail() {

		final Cor cor = new Cor();
		cor.setDescricao("Vermelho");
		this.corService.insertCor(cor);

		Assert.assertNotNull(cor);
		Assert.assertNotNull(cor.getId());
	}

	/*
	 * Atualiza cor
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void updateCorMustPass() {

		final Cor cor = corRepository.findById(1011L).orElse(null);
		cor.setDescricao("Amarelo");

		this.corService.updateCor(cor);

		final Cor corBusca = corRepository.findById(1011L).orElse(null);
		Assert.assertNotNull(corBusca);
		Assert.assertEquals("Amarelo", corBusca.getDescricao());
	}

	/*
	 * falha por atualizar, valida a unique
	 */
	@Test(expected = TransactionSystemException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void updateCorMustFail() {

		final Cor cor = new Cor();
		cor.setId(1011L);

		this.corService.updateCor(cor);
		cor.setDescricao("Azul");
	}

	/**
	 * Listar por filtros um elemento daquela coluna
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void listCoresMustPassUmDescricao() {

		final Page<Cor> cores = this.corService.listCorDescricaoByFilters("Vermelho", null);

		Assert.assertNotNull(cores);
		Assert.assertEquals(1L, cores.getTotalElements());
		Assert.assertTrue(1011L == cores.getContent().get(0).getId());
	}
	
	/**
	 * Remove cor
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void removeCorMustPass() {

		this.corService.removeCor(1011L);

		final Cor cor = corRepository.findById(1011L).orElse(null);
		Assert.assertNotNull(cor);
	}

	/**
	 * Falha ao remover cor
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/cores.sql" })
	public void removeCorMustFail() {

		this.corService.removeCor(2L);

	}


}
