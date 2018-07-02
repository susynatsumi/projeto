package br.com.eits.boot.domain.repository.floricultura.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long> {

	 @EntityGraph(attributePaths = {
	 "cor.descricao",
	 "tipoFamiliar.nomeCientifico"
	 })
	@Query("FROM Produto produto "
			+ "WHERE " 
			+ " 	(" 
			+ " 		:nomeProduto IS NULL "
			+ "	 		OR LOWER( UNACCENT( produto.nome ) ) "
			+ "	   			LIKE LOWER( '%' || UNACCENT(  CAST( :nomeProduto AS text ) )  || '%') "

			+ "		)")
	Page<Produto> listByFilters(
			@Param("nomeProduto") String nomeProduto,
			Pageable pageable);
}
