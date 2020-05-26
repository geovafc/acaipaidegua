package br.com.acaipaideguaweb.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Estabelecimento implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "O nome é obrigatório")
	@Size(min = 3, max = 100, message = "O nome deve ter no minimo 3 e no máximo 100 letras")
	private String nome;

	@NotEmpty(message = "O nome do responsável é obrigatório")
	@Size(min = 5, max = 100, message = "O nome deve ter no minimo 5 e no máximo 100 letras")
	private String responsavel;

	@Email(message = "informe um e-mail válido")
	private String email;

	private String refreshedToken;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	@Enumerated(EnumType.STRING)
	private StatusEstabelecimento status;

	private String cep;

	private String logradouro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String complemento;

	private String bairro;

	private String cidade;

	private String uf;

	private String urlImagem;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String latitude;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String longitude;

	private boolean comSelo;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String observacao;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String taxa;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean vendaAcompanhamento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private boolean podeConsumirLocal;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimento")
	private List<Telefone> telefones;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimento", orphanRemoval = true)
	private List<TabelaPreco> listaPrecos;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimento", orphanRemoval = true)
	private List<Horario> horarios;
	
	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "estabelecimento", orphanRemoval = false)
	private List<Venda> vendas;

	@JoinColumn(name = "usuario", referencedColumnName = "id")
	@ManyToOne(optional = true)
	@JsonIgnore
	private Usuario usuario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StatusEstabelecimento getStatus() {
		return status;
	}

	public void setStatus(StatusEstabelecimento status) {
		this.status = status;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public boolean isRecebido() {
		return StatusEstabelecimento.RECEBIDO.equals(this.status);
	}

	public boolean isPendente() {
		return StatusEstabelecimento.PENDENTE.equals(this.status);
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public boolean isComSelo() {
		return comSelo;
	}

	public void setComSelo(boolean comSelo) {
		this.comSelo = comSelo;
	}

	public boolean isVendaAcompanhamento() {
		return vendaAcompanhamento;
	}

	public void setVendaAcompanhamento(boolean vendaAcompanhamento) {
		this.vendaAcompanhamento = vendaAcompanhamento;
	}

	public boolean isPodeConsumirLocal() {
		return podeConsumirLocal;
	}

	public void setPodeConsumirLocal(boolean podeConsumirLocal) {
		this.podeConsumirLocal = podeConsumirLocal;
	}

	public List<Telefone> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public List<TabelaPreco> getListaPrecos() {
		return listaPrecos;
	}

	public void setListaPrecos(List<TabelaPreco> listaPrecos) {
		this.listaPrecos = listaPrecos;
	}

	public List<Horario> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<Horario> horarios) {
		this.horarios = horarios;
	}
	
		public List<Venda> getVendas() {
		return vendas;
	}

	public void setVendas(List<Venda> vendas) {
		this.vendas = vendas;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getTaxa() {
		return taxa;
	}

	public void setTaxa(String taxa) {
		this.taxa = taxa;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getRefreshedToken() {
		return refreshedToken;
	}

	public void setRefreshedToken(String refreshedToken) {
		this.refreshedToken = refreshedToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Estabelecimento other = (Estabelecimento) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

}
