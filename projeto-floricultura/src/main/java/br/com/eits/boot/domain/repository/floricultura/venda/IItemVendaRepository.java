package br.com.eits.boot.domain.repository.floricultura.venda;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.venda.ItemVenda;

public interface IItemVendaRepository extends JpaRepository<ItemVenda, Long>{

	
}
