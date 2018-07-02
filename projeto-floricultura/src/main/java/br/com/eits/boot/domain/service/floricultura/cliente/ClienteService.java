package br.com.eits.boot.domain.service.floricultura.cliente;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.cliente.Cliente;
import br.com.eits.boot.domain.repository.floricultura.cliente.IClienteRepository;

@RemoteProxy
@Service
@Transactional
public class ClienteService {

	@Autowired
	private IClienteRepository clientesRepository;

	/**
	 * Insere o cliente
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente insertCliente(Cliente cliente) {
		
		Assert.notNull(cliente, "cliente nao pode ser nulo");
		return this.clientesRepository.save(cliente);
	}

	/**
	 * Atualiza o cliente
	 * 
	 * @param cliente
	 * @return
	 */
	public Cliente updateCliente(Cliente cliente) {
		
		Assert.notNull(cliente, "cliente nao pode ser nulo");
		Assert.notNull(cliente.getId(), "id do cliente nao pode ser nulo");
		return this.clientesRepository.save(cliente);
	}

	/**
	 * Realiza consulta por filtros
	 * 
	 * @param nomeCliente
	 * @param emailCliente
	 * @param cidadeCliente
	 * @param pageRequest
	 * @return
	 */
	public Page<Cliente> listClientesByFilters(String nomeCliente, String emailCliente, String cidadeCliente,
			PageRequest pageRequest) {
		return this.clientesRepository.listByFilters(nomeCliente, emailCliente, cidadeCliente, pageRequest);
	}

	/**
	 * Remove o cliente por id
	 * 
	 * @param clienteId
	 */
	public void removeCliente(Long clienteId) {
		
		this.clientesRepository.deleteById(clienteId);
	}
}
