package br.com.acaipaideguaweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acaipaideguaweb.model.Cliente;

public interface Clientes extends JpaRepository<Cliente, Long>{
	
	public Cliente findByUid(String uid);

}
