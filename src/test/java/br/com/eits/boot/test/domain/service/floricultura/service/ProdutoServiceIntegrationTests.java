package br.com.eits.boot.test.domain.service.floricultura.service;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.boot.domain.service.floricultura.ProdutoService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ProdutoServiceIntegrationTests extends AbstractIntegrationTests {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql",
		"/dataset/floricultura/produto.sql"
	})
	public void listProdutosMustPassReturnUm() {
		final Page<Produto> produtos = this.produtoService.listProdutoByFilters(
				null, 
				null);
		
		Assert.assertNotNull(produtos);
		Assert.assertEquals(1L, produtos.getTotalElements());
	}
	
}
