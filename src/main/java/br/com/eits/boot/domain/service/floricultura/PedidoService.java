package br.com.eits.boot.domain.service.floricultura;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.repository.floricultura.IPedidoRepository;

@RemoteProxy
@Service
@Transactional
public class PedidoService {
	
	@Autowired
	public IPedidoRepository iPedidosRepository;
	
	public Pedido insertPedido(Pedido pedido) {
		
		Assert.notNull(pedido, "pedido nao pode ser nulo");
		return this.iPedidosRepository.save(pedido);
		
	}
	
	/**
	 * Edição de Pedido
	 * 
	 * @param pedido
	 * @return
	 */
	
	public Pedido updatePedido(Pedido pedido) {
		Assert.notNull(pedido, "pedido nao pode ser nulo");
		Assert.notNull(pedido.getId(), "id do pedido nao pode ser nulo");
		return this.iPedidosRepository.save(pedido);
	}

}
