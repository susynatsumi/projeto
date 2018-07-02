package br.com.eits.boot.domain.service.floricultura.produto;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.produto.TipoFamiliar;
import br.com.eits.boot.domain.repository.floricultura.produto.ITipoFamiliarRepository;

@RemoteProxy
@Service
@Transactional
public class TipoFamiliarService {

	@Autowired
	private ITipoFamiliarRepository familiarRepository;

	/**
	 * Insere o nome
	 * 
	 * @param familiar
	 * @return
	 */
	public TipoFamiliar insertNomeCientifico(TipoFamiliar familiar) {

		Assert.notNull(familiar, "Nao pode ser nulo");
		return this.familiarRepository.save(familiar);
	}

	/**
	 * Update
	 * 
	 * @param familiar
	 * @return
	 */
	public TipoFamiliar updateNomeCientifico(TipoFamiliar familiar) {

		Assert.notNull(familiar, "Nao pode ser nulo");
		Assert.notNull(familiar.getId(), "id nao pode ser nulo");
		return this.familiarRepository.save(familiar);
	}
	
	public Page<TipoFamiliar> listNomeCientificoByFilters(String nomeCientifico, PageRequest pageRequest) {
		
		return this.familiarRepository.listByFilters(nomeCientifico, pageRequest);
	}

	/**
	 * remover nomeCientifico
	 * 
	 * @param idFamiliar
	 */
	public void removeNomeCientifico(Long idFamiliar) {
		this.familiarRepository.findById(idFamiliar);
	}
}
