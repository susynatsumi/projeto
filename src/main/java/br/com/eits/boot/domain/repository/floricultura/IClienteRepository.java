package br.com.eits.boot.domain.repository.floricultura;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;

public interface IClienteRepository extends JpaRepository<Cliente, Long>{

}
