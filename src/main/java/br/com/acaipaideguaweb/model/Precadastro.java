package br.com.acaipaideguaweb.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
public class Precadastro implements Serializable {

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

	private String telefone;

	private String celular;

	@Email(message = "informe um e-mail válido")
	private String email;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date dataCadastro;

	private String site;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String latitude;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String longitude;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String logradouro;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String complemento;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String bairro;

	private String funcionamento;

	private String horario;

	private String qtdAcaiForaSafra;

	private String qtdResiduoForaSafra;

	private String qtdAcaiNaSafra;

	private String qtdResiduoNaSafra;

	private String destinoResiduo;

	@DecimalMax(value = "9999999.99", message = "Valor não pode ser maior que 9.999.999,99")
	@NumberFormat(pattern = "#,##0.00")
	private BigDecimal custoResiduo;

	private String formaParceria;

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

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
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

	public String getFuncionamento() {
		return funcionamento;
	}

	public void setFuncionamento(String funcionamento) {
		this.funcionamento = funcionamento;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}

	public String getQtdAcaiForaSafra() {
		return qtdAcaiForaSafra;
	}

	public void setQtdAcaiForaSafra(String qtdAcaiForaSafra) {
		this.qtdAcaiForaSafra = qtdAcaiForaSafra;
	}

	public String getQtdResiduoForaSafra() {
		return qtdResiduoForaSafra;
	}

	public void setQtdResiduoForaSafra(String qtdResiduoForaSafra) {
		this.qtdResiduoForaSafra = qtdResiduoForaSafra;
	}

	public String getQtdAcaiNaSafra() {
		return qtdAcaiNaSafra;
	}

	public void setQtdAcaiNaSafra(String qtdAcaiNaSafra) {
		this.qtdAcaiNaSafra = qtdAcaiNaSafra;
	}

	public String getQtdResiduoNaSafra() {
		return qtdResiduoNaSafra;
	}

	public void setQtdResiduoNaSafra(String qtdResiduoNaSafra) {
		this.qtdResiduoNaSafra = qtdResiduoNaSafra;
	}

	public String getDestinoResiduo() {
		return destinoResiduo;
	}

	public void setDestinoResiduo(String destinoResiduo) {
		this.destinoResiduo = destinoResiduo;
	}

	public BigDecimal getCustoResiduo() {
		return custoResiduo;
	}

	public void setCustoResiduo(BigDecimal custoResiduo) {
		this.custoResiduo = custoResiduo;
	}

	public String getFormaParceria() {
		return formaParceria;
	}

	public void setFormaParceria(String formaParceria) {
		this.formaParceria = formaParceria;
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
		Precadastro other = (Precadastro) obj;
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
