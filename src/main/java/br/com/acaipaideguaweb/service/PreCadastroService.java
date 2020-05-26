package br.com.acaipaideguaweb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.acaipaideguaweb.model.Precadastro;
import br.com.acaipaideguaweb.repositorio.PreCadastroRepository;
import br.com.acaipaideguaweb.repositorio.filter.PreCadastroFilter;

@Service
public class PreCadastroService {

	@Autowired
	private PreCadastroRepository service;

	public Page<Precadastro> filtrar(int page, int size) {

		Pageable pageable = new PageRequest(page, size);
		
		return (Page<Precadastro>) service.findAll(pageable);

	}
	
	public Page<Precadastro> filtrarPorNome(PreCadastroFilter filtro, int page, int size) {
		
		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		
		Pageable pageable = new PageRequest(page, size);
		
		return (Page<Precadastro>) service.findByNomeContaining(nome, pageable);
		
	}
	
	public Page<Precadastro> filtrarPorBairro(PreCadastroFilter filtro, int page, int size) {
		
		String bairro = filtro.getBairro() == null ? "%" : filtro.getBairro();
		
		Pageable pageable = new PageRequest(page, size);
		
		return (Page<Precadastro>) service.findByBairroContaining(bairro, pageable);
		
	}

	public void salvar(Precadastro preCadastro) {
		try {
			service.save(preCadastro);
		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public Precadastro buscar(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Precadastro> listar() {
		return service.findAll();
	}

}
