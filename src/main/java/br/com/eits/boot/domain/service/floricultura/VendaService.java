package br.com.eits.boot.domain.service.floricultura;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.floricultura.venda.NaturezaVenda;
import br.com.eits.boot.domain.entity.floricultura.venda.Venda;
import br.com.eits.boot.domain.repository.floricultura.IVendaRepository;

@Service
@Transactional
public class VendaService {
	
	@Autowired
	private IVendaRepository iVendaRepository;
	
	
	public Venda insertVenda(Venda venda) {
		
		venda.setNaturezaVenda(NaturezaVenda.VENDA);
		venda.setData(LocalDate.now());
		return this.iVendaRepository.save(venda);

	}
	
	public Venda updateVenda(Venda venda) {
		
		return this.iVendaRepository.save(venda);
	}

}
