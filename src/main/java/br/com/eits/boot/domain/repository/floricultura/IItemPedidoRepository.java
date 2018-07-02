package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.pedido.ItemPedido;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Long>{

}
