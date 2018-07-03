package br.com.eits.boot.domain.service.floricultura.venda;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.venda.ItemVenda;
import br.com.eits.boot.domain.repository.floricultura.venda.IItemVendaRepository;

@Service
@Transactional
@RemoteProxy
public class ItemVendaService {

	@Autowired
	private IItemVendaRepository iItemVendaRepository;
	
	public ItemVenda insertItem(ItemVenda itemVenda) {
		Assert.notNull(itemVenda, "Item não pode ser nulo");
		return this.iItemVendaRepository.save(itemVenda);
	}
}
