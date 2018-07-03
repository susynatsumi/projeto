package br.com.eits.boot.domain.repository.floricultura.venda;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.floricultura.venda.EntregaStatus;
import br.com.eits.boot.domain.entity.floricultura.venda.NaturezaVenda;
import br.com.eits.boot.domain.entity.floricultura.venda.Venda;

public interface IVendaRepository extends JpaRepository<Venda, Long> {

	@EntityGraph(attributePaths = { "itensVenda.id", "vendedor.id"})
	@Query("FROM Venda venda WHERE venda.id = :idVenda ")
	@Override
	Optional<Venda> findById(@Param("idVenda") Long id);

	@EntityGraph("itensVenda.itemVenda")
	@Query(" FROM Venda venda "
			+ " WHERE "
			+ " 	( "
			+ "			( :vendedorNome IS NULL OR :vendedorNome = '' OR venda.vendedor.nome LIKE '%' || cast( :vendedorNome as text) || '%' ) "
			+ "		AND ( :entregaStatus IS NULL OR venda.entregaStatus = :entregaStatus ) "
			+ "		AND ( :naturezaVenda IS NULL OR venda.naturezaVenda = :naturezaVenda ) "
			+ "		AND ( :valor IS NULL OR venda.valor = :valor ) "
			+ "		AND ( :valorTotal IS NULL OR venda.valorTotal = :valorTotal ) "
			+ "		AND ( :valorPago IS NULL OR venda.valorPago = :valorPago ) "
			+ "		)")
	Page<Venda> listByFilters(
			@Param("vendedorNome") String nomeVendedor,
			@Param("entregaStatus") EntregaStatus entregaStatus,
			@Param("naturezaVenda")  NaturezaVenda naturezaVenda,
			@Param("valor") Integer valor,
			@Param("valorTotal") BigDecimal valorTotal, 
			@Param("valorPago")  BigDecimal valorPago,
			Pageable pageable);

}
