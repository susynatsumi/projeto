package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.produto.Cor;

public interface ICorRepository extends JpaRepository<Cor, Long>{

}
