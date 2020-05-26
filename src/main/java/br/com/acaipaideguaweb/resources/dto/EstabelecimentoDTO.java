package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonInclude;

import br.com.acaipaideguaweb.model.Horario;
import br.com.acaipaideguaweb.model.StatusEstabelecimento;
import br.com.acaipaideguaweb.model.TabelaPreco;
import br.com.acaipaideguaweb.model.Telefone;
import br.com.acaipaideguaweb.model.Venda;


public class EstabelecimentoDTO implements Serializable{
	
	private Long id;

	
	private String nome;

	
	private String responsavel;

	private String email;

	private String refreshedToken;

	private Date dataCadastro;

	private StatusEstabelecimento status;

	private String cep;

	private String logradouro;

	private String complemento;

	private String bairro;

	private String cidade;

	private String uf;

	private String urlImagem;

	private String latitude;

	private String longitude;

	private boolean comSelo;

	private String observacao;

	private String taxa;

	private boolean vendaAcompanhamento;

	private boolean podeConsumirLocal;
	
	private List<TelefoneDTO> telefones;

	private List<TabelaPrecoDTO> listaPrecos;

	private List<HorarioDTO> horarios;
	

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

	public String getRefreshedToken() {
		return refreshedToken;
	}

	public void setRefreshedToken(String refreshedToken) {
		this.refreshedToken = refreshedToken;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public StatusEstabelecimento getStatus() {
		return status;
	}

	public void setStatus(StatusEstabelecimento status) {
		this.status = status;
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

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public List<TelefoneDTO> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<TelefoneDTO> telefones) {
		this.telefones = telefones;
	}

	public List<TabelaPrecoDTO> getListaPrecos() {
		return listaPrecos;
	}

	public void setListaPrecos(List<TabelaPrecoDTO> listaPrecos) {
		this.listaPrecos = listaPrecos;
	}

	public List<HorarioDTO> getHorarios() {
		return horarios;
	}

	public void setHorarios(List<HorarioDTO> horarios) {
		this.horarios = horarios;
	}
	
	



}
