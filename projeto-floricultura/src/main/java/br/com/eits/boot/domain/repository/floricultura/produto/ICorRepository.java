package br.com.eits.boot.domain.repository.floricultura.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.floricultura.produto.Cor;

public interface ICorRepository extends JpaRepository<Cor, Long>{

	@Query("FROM Cor cor "
			+ "WHERE "
			+ " 	("
			+ "			:descricao IS NULL "
			+ "			OR UPPER( UNACCENT( cor.descricao ) ) "
			+ "				LIKE UPPER( '%' || UNACCENT(  CAST( :descricao AS text ) )  || '%' ) "
			+ "		)")
	Page<Cor> listByFilters(
			@Param("descricao") String descricao, 
			Pageable pageable);
}
