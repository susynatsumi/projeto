package br.com.eits.boot.test.domain.service.floricultura.service.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.pedido.ItemPedido;
import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.boot.domain.service.floricultura.pedido.ItemPedidoService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ItemPedidoServiceIntegrationTests extends AbstractIntegrationTests{
	
	@Autowired
	private ItemPedidoService pedidoService;
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", 
		"/dataset/floricultura/itens_pedidos.sql" 
		})
	public void insertItensPedidosMustPass() {
		
			final Pedido pedido = new Pedido();
			pedido.setId(1001L);
			
			final Produto produto = new Produto();
			produto.setId(2004L);
			
			final ItemPedido itemPedido = new ItemPedido();
			
			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(1);
			itemPedido.setPrecoUnitario(BigDecimal.valueOf(100));
					
			
			this.pedidoService.insertItemPedido(itemPedido);
			
			Assert.assertNotNull(itemPedido);
			Assert.assertNotNull(itemPedido.getId());
		
	}
}
