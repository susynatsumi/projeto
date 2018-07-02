package br.com.eits.boot.domain.repository.floricultura.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.floricultura.produto.TipoFamiliar;

public interface ITipoFamiliarRepository extends JpaRepository<TipoFamiliar, Long>{

	@Query("FROM TipoFamiliar tipoFamiliar "
			+ "WHERE "
			+ " 	("
			+ "			:nomeCientifico IS NULL "
			+ "			OR UPPER( UNACCENT( tipoFamiliar.nomeCientifico ) ) "
			+ "				LIKE UPPER( '%' || UNACCENT(  CAST( :nomeCientifico AS text ) )  || '%' ) "
			+ "		)")
	Page<TipoFamiliar> listByFilters(
			@Param("nomeCientifico") String nomeCientifico, 
			Pageable pageable);
}
