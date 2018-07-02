package br.com.eits.boot.domain.service.floricultura.vendedor;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.vendedor.Vendedor;
import br.com.eits.boot.domain.repository.floricultura.vendedor.IVendedorRepository;

@RemoteProxy
@Service
@Transactional
public class VendedorService {

	@Autowired
	private IVendedorRepository VendedorRepository;
	
//	public Vendedor insertVendedor(Vendedor vendedor) {
	
//			Assert.notNull(vendedor, "vendedor nao pode ser nulo");
//			vendedor.setLogin("");
//			vendedor.setSenha("");
//			return this.VendedorRepository.save(vendedor);
//		}

		/**
		 * Atualiza o Vendedor
		 * 
		 * @param cliente
		 * @return
		 */
		public Vendedor updateCliente(Vendedor vendedor) {
			
			Assert.notNull(vendedor, "vendedor nao pode ser nulo");
			Assert.notNull(vendedor.getId(), "id do vendedor nao pode ser nulo");
			return this.VendedorRepository.save(vendedor);
		}
//
//		/**
//		 * Realiza consulta por filtros
//		 * 

}
