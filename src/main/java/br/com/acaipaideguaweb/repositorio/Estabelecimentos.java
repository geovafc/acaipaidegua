package br.com.acaipaideguaweb.repositorio;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Usuario;

public interface Estabelecimentos extends JpaRepository<Estabelecimento, Long> {

	public List<Estabelecimento> findByNomeContaining(String nome);

	public List<Estabelecimento> findByComSelo(Boolean selo);

	public List<Estabelecimento> findByNomeContainingAndUsuarioUsername(String nome, String userName);
	
	public List<Estabelecimento> findByUsuario(Usuario user);
	
	public Page<Estabelecimento> findByNomeContainingAndUsuarioUsernameOrderByIdAsc(String nome, String userName,Pageable pageable);
	
	public Page<Estabelecimento> findByNomeContainingOrderByIdAsc(String nome, Pageable pageable);
	
}
