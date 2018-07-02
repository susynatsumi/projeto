package br.com.eits.boot.domain.repository.floricultura.vendedor;

import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.vendedor.Vendedor;

public interface IVendedorRepository extends JpaRepository<Vendedor, Long>{
	
	@Override
	@EntityGraph(attributePaths = "venda.id")
	Optional<Vendedor> findById(Long vendaId);
}
