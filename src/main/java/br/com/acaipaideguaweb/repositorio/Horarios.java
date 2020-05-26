package br.com.acaipaideguaweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Horario;

public interface Horarios extends JpaRepository<Horario, Long> {
	
	
	List<Horario> findByEstabelecimento(Estabelecimento estabelecimento);
}
