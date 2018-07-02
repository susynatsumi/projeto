package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long>{

	
}
