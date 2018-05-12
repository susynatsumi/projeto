package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{

	@Query("FROM Cliente cliente "
			+ "WHERE "
			+ " 	("
			+ "			:nomeCliente IS NULL "
			+ "			OR UPPER( UNACCENT( cliente.nome ) ) "
			+ "				LIKE UPPER( '%' || UNACCENT(  CAST( :nomeCliente AS text ) )  || '%' ) "
			+ "		)"
			+ "		AND ("
			+ "			:emailCliente IS NULL "
			+ "			OR LOWER( UNACCENT( cliente.email ) ) "
			+ "				LIKE LOWER( '%' || UNACCENT( CAST( :emailCliente AS text ) ) || '%' ) "
			+ "			)"
			+ "		AND ("
			+ "			:cidadeCliente IS NULL "
			+ "			OR LOWER(UNACCENT( cliente.cidade ) )"
			+ "				LIKE LOWER( '%' || UNACCENT( CAST( :cidadeCliente AS text ) ) || '%' ) "
			+ "		)")
	Page<Cliente> listByFilters(
			@Param("nomeCliente") String nomeCliente, 
			@Param("emailCliente") String emailCliente, 
			@Param("cidadeCliente") String cidadeCliente, 
			Pageable pageRequest);


}
