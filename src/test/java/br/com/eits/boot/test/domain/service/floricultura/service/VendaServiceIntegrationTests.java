package br.com.eits.boot.test.domain.service.floricultura.service;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.boot.domain.service.floricultura.VendaService;
import br.com.eits.boot.test.domain.AbstractIntegrationTests;


public class VendaServiceIntegrationTests extends AbstractIntegrationTests {
	
	@Autowired
	private VendaService vendaService;

//	@Autowired
//	private IVendaRepository iVendaRepository;
	
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
	
//	public void insertVendaMustPassVerifySituacao() {
//		
//		final Venda venda = new Venda();
//		venda.setData(LocalDate.now());
//		venda.setValor(new BigDecimal(2000.00));
//		venda.setValorTotal(new BigDecimal(3000.00));
//		venda.setValorPago(new BigDecimal(4000.00));
//		this.vendaService.insertVenda(venda);
//		
//	}
}
