package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;

public interface IProdutoRepository extends JpaRepository<Produto, Long>{

}
