package br.com.acaipaideguaweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acaipaideguaweb.model.Venda;

public interface Vendas extends JpaRepository<Venda, Long>{

	public List<Venda> findByOrderByIdDesc();
}
