package br.com.eits.boot.domain.service.floricultura.pedido;

import org.castor.core.util.Assert;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.floricultura.pedido.ItemPedido;
import br.com.eits.boot.domain.repository.floricultura.pedido.IItemPedidoRepository;

@RemoteProxy
@Service
@Transactional
public class ItemPedidoService {

	@Autowired
	private IItemPedidoRepository itemPedidoRepository;

	/**
	 * Insere itens no pedido
	 * 
	 * @param itemPedido
	 * @return
	 */
	public ItemPedido insertItemPedido(ItemPedido itemPedido) {

		Assert.notNull(itemPedido, "O item pedido nao pode ser nulo");

		return this.itemPedidoRepository.save(itemPedido);
	}

	/**
	 * Realiza update
	 * 
	 * @param produto
	 * @return
	 */
	public ItemPedido updateItemPedido(ItemPedido itemPedido) {

		Assert.notNull(itemPedido, "O item pedido não pode ser nulo");
		Assert.notNull(itemPedido.getId(), "Id do item pedido não pode ser nulo");
		return this.itemPedidoRepository.save(itemPedido);
	}

	public ItemPedido findItemPedidoById(Long id) {

		final ItemPedido itemPedido = itemPedidoRepository.findById(id).orElse(null);
		Assert.notNull(itemPedido, "item pedido não encontrado");

		return itemPedido;
	}

	/**
	 * 
	 * Realiza a busca por Id
	 * 
	 * @param id
	 * @return
	 * 
	 */
	@Transactional(readOnly = true)
	public ItemPedido findItemPedidoById(long id) {

		return this.itemPedidoRepository.findById(id).orElse(null);

	}
//
//	@Transactional(readOnly = true)
//	public List<ItemPedido> listItemPedidoByProdutos(long idItem) {
//		return this.itemPedidoRepository.findById(idItem);
//	}
	
	//TODO fazer lista por filtros

	/**
	 * Remove pelo id
	 * 
	 * @param id
	 */
	public void removeItemPedido(Long id) {

		this.itemPedidoRepository.deleteById(id);
	}
}