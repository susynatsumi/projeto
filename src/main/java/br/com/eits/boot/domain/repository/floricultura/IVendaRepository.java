package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.venda.Venda;

public interface IVendaRepository extends JpaRepository<Venda, Long>{

}
