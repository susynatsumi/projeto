package br.com.eits.boot.test.domain.service.floricultura.service.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.pedido.ItemPedido;
import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.service.floricultura.pedido.ItemPedidoService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class ItemPedidoServiceIntegrationTests extends AbstractIntegrationTests{
	
	private ItemPedidoService pedidoService;
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql",
		"/dataset/floricultura/clientes.sql",
		"/dataset/floricultura/pedidos.sql", 
		"/dataset/floricultura/item_pedidos.sql" 
		})
	public void insertItensPedidosMustPass() {
		
			final Pedido pedido = new Pedido();
			final ItemPedido itemPedido = new ItemPedido();
			
			
			pedido.setTotalItens(new Double(10));
			pedido.setDataPedido(LocalDate.of(2018, Month.APRIL, 16));
			pedido.setPrecoTotal(new BigDecimal(50.00));
			
			this.pedidoService.insertItemPedido(itemPedido);
			
			Assert.assertNotNull(itemPedido);
			Assert.assertNotNull(itemPedido.getId());
		
	}
}
