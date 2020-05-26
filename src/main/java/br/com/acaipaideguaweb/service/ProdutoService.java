package br.com.acaipaideguaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.acaipaideguaweb.model.Produto;
import br.com.acaipaideguaweb.repositorio.Produtos;

@Service
public class ProdutoService {

	@Autowired
	private Produtos produtos;
	
	public Page<Produto> findByPagination(int page, int size){
		Pageable pageable = new PageRequest(page, size);
		return produtos.findAllByOrderByIdAsc(pageable);
	}
	
	public void salvar(Produto produto) {
		try {
			produtos.save(produto);

		} catch (Exception e) {
			throw new IllegalArgumentException("Erro cadastrar produto");
		}
	}
	
	public void excluir(Long id) {
		produtos.delete(id);

	}
	
	public List<Produto> listar(){
		return produtos.findAll();
	}
	
	public Produto buscar(Long id){
		return produtos.findOne(id);
	}

	public Produto findById(Long id) {
		return produtos.findOne(id);
	}
}
