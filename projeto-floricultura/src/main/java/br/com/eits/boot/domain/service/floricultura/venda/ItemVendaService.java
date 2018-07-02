package br.com.eits.boot.domain.service.floricultura.venda;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.pedido.ItemPedido;
import br.com.eits.boot.domain.repository.floricultura.pedido.IItemPedidoRepository;

public class ItemVendaService {

	@Autowired
	private IItemPedidoRepository pedidoRepository;
	
	public ItemPedido insertItensPedido(ItemPedido itemPedido) {
		Assert.notNull(itemPedido, "cliente nao pode ser nulo");
		return this.pedidoRepository.save(itemPedido);
	}
}
