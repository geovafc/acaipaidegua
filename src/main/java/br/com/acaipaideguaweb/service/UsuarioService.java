package br.com.acaipaideguaweb.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Usuario;
import br.com.acaipaideguaweb.model.UsuarioRole;
import br.com.acaipaideguaweb.repositorio.Estabelecimentos;
import br.com.acaipaideguaweb.repositorio.UsuarioRepository;
import br.com.acaipaideguaweb.repositorio.UsuarioRoleRepository;
import br.com.acaipaideguaweb.repositorio.filter.UsuarioEstabelecimentoFilter;

@Service
public class UsuarioService {

	private static final Logger LOG = Logger.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	UsuarioRoleRepository roleRepository;

	@Autowired
	private Estabelecimentos estabelecimentoRepositorio;

	@Transactional(readOnly = false)
	public void updateSenha(Usuario usuario) {

		try {

			String hash = new BCryptPasswordEncoder().encode(usuario.getPassword());

			usuario.setPassword(hash);

			repository.updateSenha(usuario.getPassword(), usuario.getId());

		} catch (Exception e) {
			LOG.error("Ocorreu erro em UsuarioSrvice" + e.getMessage());
		}

	}

	@Transactional(readOnly = false)
	public void updateNomeAndEmail(Usuario usuario) {

		repository.updateNomeAndEmail(usuario.getUsername(), usuario.getEmail(), usuario.isEnabled(), usuario.getId());
	}

	@Transactional(readOnly = false)
	public Usuario save(Usuario usuario) {

		if (usuario.getDataCadastro() == null) {
			usuario.setDataCadastro(Date.valueOf(LocalDate.now()));
			usuario.setEnabled(Boolean.TRUE);
			;
		}

		String hash = new BCryptPasswordEncoder().encode(usuario.getPassword());

		usuario.setPassword(hash);

		return repository.save(usuario);
	}

	@Transactional(readOnly = false)
	public void saveRole(UsuarioRole role) {
		roleRepository.save(role);
	}

	@Transactional(readOnly = false)
	public void delete(Long id) {

		repository.delete(id);
	}

	public Usuario findById(Long id) {

		return repository.findOne(id);
	}

	public Usuario findByEmail(String email) {

		return repository.findByEmail(email);
	}

	public List<Usuario> findAll() {

		return repository.findAll();
	}

	public List<String> findByRoles(Usuario usuario) {
		List<String> roles = new ArrayList<>();

		List<UsuarioRole> autorizacoes = roleRepository.findByUsuario(usuario);

		for (UsuarioRole autorizacao : autorizacoes) {
			roles.add(autorizacao.getPerfil().toString());
		}
		return roles;
	}
	
	public Usuario findByEmailAndPassword(String email, String password) {

		return repository.findByEmailAndPassword(email, password);
	}

	public void excluir(Long id) {

		repository.delete(id);

	}

	public Estabelecimento filtrar(UsuarioEstabelecimentoFilter filtro) {
		Long codigo = filtro.getCodigo() == null ? null : filtro.getCodigo();
		return estabelecimentoRepositorio.findOne(codigo);
	}

	public void salvarUsuarioEstabelecimento(Estabelecimento estabelecimento) {

		estabelecimentoRepositorio.save(estabelecimento);

	}

	public Usuario findByUsername(String nome) {
		return repository.findByUsername(nome);
	}

}
