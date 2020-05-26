package br.com.acaipaideguaweb.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.acaipaideguaweb.model.Usuario;
import br.com.acaipaideguaweb.model.UsuarioRole;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Long> {

	@Modifying
	@Query("select a.perfil from UsuarioRole a, Usuario b where b.email=?1 and a.usuario=b.id")
	public List<String> findRoleByEmail(String email);

	public List<UsuarioRole> findByUsuario(Usuario usuario);
}
