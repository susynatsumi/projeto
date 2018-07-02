package br.com.eits.boot.domain.service.floricultura.produto;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.produto.Cor;
import br.com.eits.boot.domain.repository.floricultura.produto.ICorRepository;

@RemoteProxy
@Service
@Transactional
public class CorService {

	@Autowired
	private ICorRepository corRepository;

	/**
	 * Insere cor
	 * 
	 * @param cor
	 * @return
	 */
	public Cor insertCor(Cor cor) {

		Assert.notNull(cor, "Nao pode ser nulo");
		return this.corRepository.save(cor);
	}

	/**
	 * Update cor
	 * 
	 * @param cor
	 * @return
	 */
	public Cor updateCor(Cor cor) {

		Assert.notNull(cor, "Nao pode ser nulo");
		Assert.notNull(cor.getId(), "id nao pode ser nulo");
		return this.corRepository.save(cor);
	}

	/**
	 * Listar por filtros
	 * 
	 * @param descricao
	 * @param pageRequest
	 * @return
	 */
	public Page<Cor> listCorDescricaoByFilters(String descricao, PageRequest pageRequest) {
		
		return this.corRepository.listByFilters(descricao, pageRequest);
		
	}

	/**
	 * remove cor
	 * 
	 * @param corId
	 */
	public void removeCor(Long corId) {
		
		this.corRepository.findById(corId);
	}
}
