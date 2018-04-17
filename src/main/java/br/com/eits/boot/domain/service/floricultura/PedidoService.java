package br.com.eits.boot.domain.service.floricultura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;
import br.com.eits.boot.domain.repository.floricultura.IPedidoRepository;

@Service
@Transactional
public class PedidoService {
	
	@Autowired
	public IPedidoRepository iPedidosRepository;
	
	public Pedido insertPedido(Pedido pedido) {
		
		
		return this.iPedidosRepository.save(pedido);
		
	}
	
	/**
	 * Edição de Pedido
	 * 
	 * @param pedido
	 * @return
	 */
	
	public Pedido updatePedido(Pedido pedido) {
		return this.iPedidosRepository.save(pedido);
	}

}
