package br.com.eits.boot.test.domain.service.floricultura.service.venda;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.boot.domain.entity.floricultura.venda.ItemVenda;
import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.boot.domain.repository.floricultura.venda.IItemVendaRepository;
import br.com.eits.boot.domain.service.floricultura.venda.ItemVendaService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ItemVendaServiceIntegrationTests extends AbstractIntegrationTests{

	@Autowired
	private ItemVendaService itemVendaService;
	
	@Autowired
	private IItemVendaRepository itemVendaRepository;

	@Test()
	@WithUserDetails("admin@email.com")
	@Sql({ 
		"/dataset/account/users.sql", 
		"/dataset/floricultura/itens_vendas.sql" 
	})
	public void insertClienteMustPass() {
		
		final Produto produto = new Produto();
		produto.setId(2004L);
		
		final Venda venda = new Venda();
		venda.setId(200L);
		
		final ItemVenda itemVenda = new ItemVenda();
		itemVenda.setProduto(produto);
		itemVenda.setPrecoUnitario(BigDecimal.valueOf(100));
		itemVenda.setQuantidade(1);
		itemVenda.setVenda(venda);
		
		this.itemVendaService.insertItem(itemVenda);
		
	}
	
}
