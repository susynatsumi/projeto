package br.com.eits.boot.domain.repository.floricultura.pedido;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.pedido.Pedido;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {

	@Override
	@EntityGraph(attributePaths = "cliente.nome")
	Optional<Pedido> findById(Long pedidoId);
	
}
