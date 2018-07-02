package br.com.eits.boot.domain.repository.floricultura.pedido;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.pedido.ItemPedido;

public interface IItemPedidoRepository extends JpaRepository<ItemPedido, Long> {

//	@EntityGraph(attributePaths = { "pedido.id" })
//	@Query("FROM ItemPedido itemPedido WHERE itemPedido.id = :idItemPedido")
//	Optional<ItemPedido> findByIdItemPedidoWithPedido(@Param("idItemPedido") Long id);
//
//	@EntityGraph(attributePaths = { "produto.id", "produto.nome" })
//	@Query("FROM ItemPedido itemPedido WHERE itemPedido.id = :idItemPedido")
//	Optional<ItemPedido> findByIdItemPedidoWithProduto(@Param("idItemPedido") Long id);
//
//	@Query("FROM ItemPedido itemPedido WHERE itemPedido.id = :idItemPedido")
//	Optional<ItemPedido> findByIdWithLivro(@Param("idExemplar") Long id);

}
