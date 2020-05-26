package br.com.acaipaideguaweb.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.acaipaideguaweb.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	Usuario findByEmail(String email);

	Usuario findByUsername(String nome);
	
	Usuario findByEmailAndPassword(String email, String password);

	@Modifying
	@Query("update Usuario u set u.username = ?1, u.email = ?2, u.enabled = ?3 where u.id = ?4")
	void updateNomeAndEmail(String username, String email, boolean enabled, Long id);

	@Modifying
	@Query("update Usuario u set u.password = ?1 where u.id = ?2")
	void updateSenha(String senha, Long id);

}
