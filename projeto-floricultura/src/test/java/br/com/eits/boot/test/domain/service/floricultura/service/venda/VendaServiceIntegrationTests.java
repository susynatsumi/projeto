package br.com.eits.boot.test.domain.service.floricultura.service.venda;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.jdbc.Sql;

import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.boot.domain.service.floricultura.venda.VendaService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;


public class VendaServiceIntegrationTests extends AbstractIntegrationTests {
	
	@Autowired
	private VendaService vendaService;

//	@Autowired
//	private IVendaRepository iVendaRepository;
	
	
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/vendedores.sql",
		"/dataset/floricultura/vendas.sql"
	})
	public void insertVendaMustPass() {
		
		final Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setValor(new BigDecimal(2000.00));
		venda.setValorTotal(new BigDecimal(3000.00));
		venda.setValorPago(new BigDecimal(4000.00));
		this.vendaService.insertVenda(venda);
		
		
		Assert.assertNotNull(venda);
		Assert.assertNotNull(venda.getData());
	}
	
	@Test
	@WithUserDetails("admin@email.com")
	@Sql({
		"/dataset/account/users.sql",
		"/dataset/floricultura/vendedores.sql",
		"/dataset/floricultura/vendas.sql"
	})
	public void insertVendaMustPassVerifySituacao() {
		
		final Venda venda = new Venda();
		venda.setData(LocalDate.now());
		venda.setValor(new BigDecimal(2000.00));
		venda.setValorTotal(new BigDecimal(3000.00));
		venda.setValorPago(new BigDecimal(4000.00));
		this.vendaService.insertVenda(venda);
		
	}
}
