package br.com.eits.boot.test.domain.service.floricultura.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.repository.floricultura.IPedidoRepository;
import br.com.eits.boot.domain.service.floricultura.PedidoService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class PedidoServiceIntegrationTests extends AbstractIntegrationTests{
	
	
	@Autowired
	private PedidoService pedidoService;
	
	private IPedidoRepository iPedidosRepository;

	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql",
		"/dataset/floricultura/pedido.sql"
	})
	public void insertPedidoMustPass() {
		
		final Pedido pedido = new Pedido();
		pedido.setTotalItens(new Double(1000));
		pedido.setDataPedido(LocalDate.now());
		pedido.setPrecoTotal(new BigDecimal(500.00));
		this.pedidoService.insertPedido(pedido);
		
		Assert.assertNotNull(pedido);
		Assert.assertNotNull(pedido.getId());
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/cliente.sql",
		"/dataset/floricultura/pedido.sql"
	})
	public void updatePedidoMustPass() { //atualiza
		
		final Pedido pedido = this.iPedidosRepository.findById(1002L).orElse(null);
		pedido.setTotalItens(new Double(2000));
		pedido.setPrecoTotal(new BigDecimal(600.00));
		this.pedidoService.updatePedido(pedido);
		
		Assert.assertNotNull(pedido);	
		final Pedido pedidoAlterado = this.iPedidosRepository.findById(1002L).orElse(null);
		Assert.assertNotNull(pedidoAlterado);
		Assert.assertEquals(pedidoAlterado.getTotalItens(), "2000");
		Assert.assertEquals(pedidoAlterado.getPrecoTotal(), "600.00");
		Assert.assertNotNull(pedido.getId());
		
	}
	
	
}
