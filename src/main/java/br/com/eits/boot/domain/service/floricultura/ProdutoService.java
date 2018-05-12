package br.com.eits.boot.domain.service.floricultura;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.boot.domain.repository.floricultura.IProdutoRepository;

@RemoteProxy
@Service
@Transactional
public class ProdutoService {
	
	@Autowired
	private IProdutoRepository produtosRepository;
	
	public Page<Produto> listProdutoByFilters(
			String nomeProduto,
			PageRequest pageRequest 
			) {
		return this.produtosRepository.listByFilters(
				nomeProduto, 
				pageRequest);
	}

}
