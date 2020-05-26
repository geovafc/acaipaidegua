package br.com.acaipaideguaweb.repositorio;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acaipaideguaweb.model.Produto;

public interface Produtos extends JpaRepository<Produto, Long>{ 
	
	Page<Produto> findAllByOrderByIdAsc(Pageable pageable);

}
