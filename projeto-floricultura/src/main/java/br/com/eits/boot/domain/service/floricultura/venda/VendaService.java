package br.com.eits.boot.domain.service.floricultura.venda;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.floricultura.venda.EntregaStatus;
import br.com.eits.boot.domain.entity.floricultura.venda.ItemVenda;
import br.com.eits.boot.domain.entity.floricultura.venda.NaturezaVenda;
import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.boot.domain.repository.floricultura.venda.IVendaRepository;

@RemoteProxy
@Service
@Transactional
public class VendaService {
	
	@Autowired
	private IVendaRepository vendaRepository;
	
	
	public Venda insertVenda(Venda venda) {
		
		venda.setNaturezaVenda(NaturezaVenda.VENDA);
		venda.setData(LocalDate.now());
		return this.vendaRepository.save(venda);
	}
	
	public Venda updateVenda(Venda venda) {
		
		return this.vendaRepository.save(venda);
	}
	
	//mock de item venda
		public List<ItemVenda> mockItensVenda() {
		
		final Venda venda = new Venda();
		venda.setId(200L);
		
		
		final ItemVenda itemVenda = new ItemVenda();
		itemVenda.setPrecoUnitario(new BigDecimal(200.00));
		itemVenda.setQuantidade(new Integer(10));
		return Arrays.asList(itemVenda);
		
	}
	

	public Page<Venda> listByFiltersVenda(
			String nomeVendedor,
			EntregaStatus entregaStatus,
			NaturezaVenda naturezaVenda,
			Integer valor,
			BigDecimal valorTotal,
			BigDecimal valorPago,
			PageRequest pageRequest)

	{
		return this.vendaRepository.listByFilters(
				nomeVendedor, 
				entregaStatus,
				naturezaVenda, 
				valor, 
				valorTotal,
				valorPago,
				pageRequest);
	}
	
	public void removeVenda(Long id) {
		
		this.vendaRepository.deleteById(id);
	}
}
