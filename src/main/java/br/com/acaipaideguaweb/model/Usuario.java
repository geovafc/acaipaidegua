package br.com.acaipaideguaweb.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Usuario extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O nome é obrigatório")
	@Column(nullable = false, unique = true)
	private String username;

	@NotEmpty(message = "O email é obrigatório")
	@Column(nullable = false, unique = true)
	private String email;

	@NotEmpty(message = "A senha é obrigatório")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "password", nullable = false)
	private String password;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	@Column(name = "data_cadastro", nullable = false)
	private Date dataCadastro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Column(name = "enabled")
	private boolean enabled;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = true)
	private List<UsuarioRole> roles;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "usuario", orphanRemoval = false)
	private List<Estabelecimento> listaEstabelecimentos;

	public Usuario() {

	}

	public Usuario(Usuario user) {
		super.setId(user.getId());
		this.username = user.username;
		this.email = user.email;
		this.password = user.password;
		this.enabled = user.enabled;
	}

	@Override
	public void setId(Long id) {
		super.setId(id);
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public List<UsuarioRole> getRoles() {
		return roles;
	}

	public void setRoles(List<UsuarioRole> roles) {
		this.roles = roles;
	}

	public List<Estabelecimento> getListaEstabelecimentos() {
		return listaEstabelecimentos;
	}

	public void setListaEstabelecimentos(List<Estabelecimento> listaEstabelecimentos) {
		this.listaEstabelecimentos = listaEstabelecimentos;
	}

}
