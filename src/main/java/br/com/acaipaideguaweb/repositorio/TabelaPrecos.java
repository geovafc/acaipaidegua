package br.com.acaipaideguaweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.TabelaPreco;
import br.com.acaipaideguaweb.model.Telefone;

public interface TabelaPrecos extends JpaRepository<TabelaPreco, Long> {

	List<TabelaPreco> findByEstabelecimento(Estabelecimento estabelecimento);
}
