package br.com.acaipaideguaweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acaipaideguaweb.model.ItemVenda;
import br.com.acaipaideguaweb.model.Venda;

public interface ItensVendasRepository extends JpaRepository<ItemVenda, Long>{
	
	public List<ItemVenda> findAllByVenda(Venda venda);

}
