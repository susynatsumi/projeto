package br.com.eits.boot.test.domain.service.floricultura.service.pedido;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;
import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.repository.floricultura.pedido.IPedidoRepository;
import br.com.eits.boot.domain.service.floricultura.pedido.PedidoService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;

public class PedidoServiceIntegrationTests extends AbstractIntegrationTests {

	@Autowired
	private PedidoService pedidoService;

	@Autowired
	private IPedidoRepository pedidosRepository;

	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql",
		"/dataset/floricultura/pedidos.sql"
	})
	public void insertPedidoMustPass() {
	
		final Cliente cliente = new Cliente();
		cliente.setId(1000L);
		
		final Pedido pedido = new Pedido();
		pedido.setId(1001L);
		pedido.setTotalItens(new Double(1000));
		pedido.setDataPedido(LocalDate.of(2015, Month.FEBRUARY, 9));
		pedido.setPrecoTotal(new BigDecimal(500.00));
		pedido.setCliente(cliente);
		
		this.pedidoService.insertPedido(pedido);

		Assert.assertNotNull(pedido);
		Assert.assertNotNull(pedido.getId());
	}

	@Test(expected = DataIntegrityViolationException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql",
		"/dataset/floricultura/clientes.sql",
		"/dataset/floricultura/pedidos.sql"
	})
	public void insertPedidoMustFailCliente() {
	
//		final Cliente cliente = new Cliente();
//		cliente.setId(1000L);
		
		final Pedido pedido = new Pedido();
		pedido.setId(1001L);
		pedido.setTotalItens(new Double(1000));
		pedido.setDataPedido(LocalDate.of(2015, Month.FEBRUARY, 9));
		pedido.setPrecoTotal(new BigDecimal(500.00));
//		pedido.setCliente(cliente);
		
		this.pedidoService.insertPedido(pedido);

		Assert.assertNotNull(pedido);
		Assert.assertNotNull(pedido.getId());
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", 
		"/dataset/floricultura/pedidos.sql" })
	public void updatePedidoMustPassTotalItens() { // atualiza

		final Cliente cliente = new Cliente();
		cliente.setId(1000L);
		
		final Pedido pedido = this.pedidosRepository.findById(1002L).orElse(null);
		pedido.setId(1001L);
		pedido.setTotalItens(new Double(10));
		pedido.setDataPedido(LocalDate.of(2015, Month.FEBRUARY, 9));
		pedido.setPrecoTotal(new BigDecimal(500.00));
		pedido.setCliente(cliente);
		
		this.pedidoService.updatePedido(pedido);

		final Pedido pedidoItemAlterado = this.pedidosRepository.findById(1001L).orElse(null);
		Assert.assertNotNull(pedidoItemAlterado);
		Assert.assertEquals(new Double(10), pedidoItemAlterado.getTotalItens());
		

	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", 
		"/dataset/floricultura/clientes.sql",
		"/dataset/floricultura/pedidos.sql" })
	public void updatePedidoMustPassDataPedido() { // atualiza

		final Cliente cliente = new Cliente();
		cliente.setId(1000L);
		
		final Pedido pedido = this.pedidosRepository.findById(1001L).orElse(null);
		pedido.setId(1001L);
		pedido.setTotalItens(new Double(1000));
		pedido.setDataPedido(LocalDate.of(2018, Month.MAY, 7));
		pedido.setPrecoTotal(new BigDecimal(500.00));
		pedido.setCliente(cliente);
		
		this.pedidoService.updatePedido(pedido);

		final Pedido pedidoDataAlterada = this.pedidosRepository.findById(1001L).orElse(null);
		Assert.assertNotNull(pedidoDataAlterada);
		Assert.assertEquals(LocalDate.of(2018, Month.MAY, 7), pedidoDataAlterada.getDataPedido());
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", 
		"/dataset/floricultura/clientes.sql",
		"/dataset/floricultura/pedidos.sql" })
	public void removePedidoMustPass() {

		this.pedidoService.removePedido(1001L);

		final Pedido pedido = this.pedidosRepository.findById(1001L).orElse(null);

		Assert.assertNull(pedido);
	}
	
	/**
	 * Falha para a remoção do pedido, pois não foi encontrado resultado para consulta
	 */
	@Test(expected = EmptyResultDataAccessException.class)
	@WithUserDetails("admin@email.com")
	@Sql({ "/dataset/account/users.sql", 
		"/dataset/floricultura/clientes.sql",
		"/dataset/floricultura/pedidos.sql" })
	public void removePedidoMustFail() {

		this.pedidoService.removePedido(100L);
		
		final Pedido pedido = this.pedidosRepository.findById(100L).orElse(null);

		Assert.assertNull(pedido);
	}

}
