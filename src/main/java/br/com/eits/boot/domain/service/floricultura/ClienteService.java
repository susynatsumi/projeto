package br.com.eits.boot.domain.service.floricultura;


import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;
import br.com.eits.boot.domain.repository.floricultura.IClienteRepository;

@RemoteProxy
@Service
@Transactional
public class ClienteService {
	
	@Autowired
	private IClienteRepository clientesRepository;

	public Cliente insertCliente(Cliente cliente) {
		
		Assert.notNull(cliente, "cliente nao pode ser nulo");
		return this.clientesRepository.save(cliente);
		
	}
	
	public Cliente updateCliente(Cliente cliente) {
		Assert.notNull(cliente, "cliente nao pode ser nulo");
		Assert.notNull(cliente.getId(), "id do cliente nao pode ser nulo");
		return this.clientesRepository.save(cliente);
	}

	public Page<Cliente> listClientesByFilters(
			String nomeCliente,
			String emailCliente,
			String cidadeCliente,
			PageRequest pageRequest 
			) {
		return this.clientesRepository.listByFilters(
				nomeCliente, 
				emailCliente,
				cidadeCliente,
				pageRequest);
	}
	
	public void removeCliente(Long clienteId) {
		this.clientesRepository.deleteById(clienteId);
	}
}
