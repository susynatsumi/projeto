package br.com.eits.boot.domain.service.floricultura.produto;

import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.eits.boot.domain.entity.floricultura.produto.Produto;
import br.com.eits.boot.domain.repository.floricultura.produto.IProdutoRepository;

@RemoteProxy
@Service
@Transactional
public class ProdutoService {

	@Autowired
	private IProdutoRepository produtosRepository;

	// Realiza inserção

	public Produto insertProduto(Produto produto) {

		Assert.notNull(produto, "produto nao pode ser nulo");
		Assert.notNull(produto.getCor(), "id da cor nao pode ser nulo");
		Assert.notNull(produto.getFamiliar(), "id nome cientifico nao pode ser nulo");

		return this.produtosRepository.save(produto);
	}

	/**
	 * Realiza update
	 * 
	 * @param produto
	 * @return
	 */
	public Produto updateProduto(Produto produto) {

		Assert.notNull(produto, "produto nao pode ser nulo");
		Assert.notNull(produto.getId(), "id do produto nao pode ser nulo");
		return this.produtosRepository.save(produto);
	}

	/**
	 * Busca por o produto por filtros
	 * 
	 * @param nomeProduto
	 * @param pageRequest
	 * @return
	 */
	public Page<Produto> listProdutoByFilters(String nome, PageRequest pageRequest) {
		return this.produtosRepository.listByFilters(nome, pageRequest);
	}

	/**
	 * Deleta o produto por id
	 * 
	 * @param idProduto
	 */
	public void remove(long idProduto) {
		this.produtosRepository.deleteById(idProduto);
	}

}
