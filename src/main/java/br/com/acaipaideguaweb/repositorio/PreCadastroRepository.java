package br.com.acaipaideguaweb.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acaipaideguaweb.model.Precadastro;

public interface PreCadastroRepository extends JpaRepository<Precadastro, Long>{

	public Page<Precadastro> findByNomeContaining(String nome,Pageable pageable);
	
	public Page<Precadastro> findByBairroContaining(String bairro,Pageable pageable);
	
}
