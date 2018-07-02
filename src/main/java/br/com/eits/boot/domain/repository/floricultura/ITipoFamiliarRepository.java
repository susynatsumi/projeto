package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.produto.TipoFamiliar;

public interface ITipoFamiliarRepository extends JpaRepository<TipoFamiliar, Long>{

}
