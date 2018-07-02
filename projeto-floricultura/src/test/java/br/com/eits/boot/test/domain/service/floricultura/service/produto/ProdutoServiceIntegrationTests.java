package br.com.eits.boot.test.domain.service.floricultura.service.produto;

import javax.validation.ValidationException;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.produto.Cor;
import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.boot.domain.entity.floricultura.produto.TipoFamiliar;
import br.com.eits.boot.domain.repository.floricultura.produto.IProdutoRepository;
import br.com.eits.boot.domain.service.floricultura.produto.ProdutoService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ProdutoServiceIntegrationTests extends AbstractIntegrationTests {

	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private IProdutoRepository produtoRepository;

	/**
	 * 
	 * A cor e o familiar precisa ser definidade
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/produtos.sql" })
	public void insertProdutosMustPass() {

		final Produto produto = new Produto();
		
		final Cor cor = new Cor();
		cor.setDescricao("Vermelho");
		
		produto.setCor(cor);
		
		final TipoFamiliar familiar = new TipoFamiliar();
		familiar.setNomeCientifico("Leucanthemum vulgare");
		
		produto.setFamiliar(familiar);
		
		this.produtoService.insertProduto(produto);
		
		Assert.assertNotNull(produto);
		Assert.assertNotNull(produto.getId());
		Assert.assertNotNull(cor.getId());
		Assert.assertNotNull(familiar.getId());	
	}
	
	/**
	 * 
	 * A cor e o familiar precisa ser definidade
	 */
	@Test(expected = ValidationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/produtos.sql" })
	public void insertProdutosMustFailWithHeritage() {

		final Produto produto = new Produto();
		this.produtoService.insertProduto(produto);
		Assert.assertNotNull(produto);
	}

//	@Test
//	@WithUserDetails("admin@email.com")
//	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/produtos.sql" })
//	public void listProdutosMustPassReturnNome() {
//		
//		final Page<Produto> produtos = this.produtoService.listProdutoByFilters("Margarida", null, null, null);
//
//		Assert.assertNotNull(produtos);
//		Assert.assertEquals(1L, produtos.getTotalElements());
//		Assert.assertTrue(2004L == produtos.getContent().get(0).getId());
//
//
//	}
	
	/**
	 * 
	 * A cor e o familiar precisa ser definidade
	 */
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", "/dataset/floricultura/produtos.sql" })
	public void removeProdutosMustPass(){

		this.produtoService.remove(2004L);

		final Produto produto = this.produtoRepository.findById(2004L).orElse(null);
		Assert.assertNull(produto);
	}

}
