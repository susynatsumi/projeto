package br.com.eits.boot.domain.service.floricultura.pedido;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.repository.floricultura.pedido.IPedidoRepository;

@RemoteProxy
@Service
@Transactional
public class PedidoService {

	@Autowired
	private IPedidoRepository pedidosRepository;

	/**
	 * Insert de Pedido
	 * 
	 * @param pedido
	 * @return
	 */
	public Pedido insertPedido(Pedido pedido) {

		Assert.notNull(pedido, "pedido não pode ser nulo");
		return this.pedidosRepository.save(pedido);

	}
	
	/**
	 * Update de Pedido
	 * 
	 * @param pedido
	 * @return
	 */
	public Pedido updatePedido(Pedido pedido) {

		Assert.notNull(pedido, "pedido nao pode ser nulo");
		Assert.notNull(pedido.getId(), "id do pedido nao pode ser nulo");
		return this.pedidosRepository.save(pedido);
	}

	/**
	 * retorna um id de pedido
	 * 
	 * @param id
	 * @return
	 */
	public Pedido findByIdPedido(Long pedidoId) {

		final Pedido pedido = pedidosRepository.findById(pedidoId).orElse(null);
		Assert.notNull(pedido, "Pedido não encontrado.");
		
		return pedido;
	}

	/**
	 * Remove pelo id
	 * 
	 * @param idPedido
	 * @return
	 */
	public void removePedido(Long id) {

		this.pedidosRepository.deleteById(id);
	}
}
