package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.vendedor.Vendedor;

public interface IVendedorRepository extends JpaRepository<Vendedor, Long>{

}
