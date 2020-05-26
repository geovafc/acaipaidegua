package br.com.acaipaideguaweb.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Horario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Enumerated(EnumType.STRING)
	private TipoHorario tipo;

	private String diaInicio;

	private String diaFinal;

	@NotNull(message = "Informe a hora de abertura")
	private String abertura;

	@NotNull(message = "Informe a hora de fechamento")
	private String fechamento;

	@JoinColumn(name = "estabelecimento", referencedColumnName = "id")
	@ManyToOne(optional = false)
	@JsonIgnore
	private Estabelecimento estabelecimento;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoHorario getTipo() {
		return tipo;
	}

	public void setTipo(TipoHorario tipo) {
		this.tipo = tipo;
	}

	public String getDiaInicio() {
		return diaInicio;
	}

	public void setDiaInicio(String diaInicio) {
		this.diaInicio = diaInicio;
	}

	public String getDiaFinal() {
		return diaFinal;
	}

	public void setDiaFinal(String diaFinal) {
		this.diaFinal = diaFinal;
	}

	public String getAbertura() {
		return abertura;
	}

	public void setAbertura(String abertura) {
		this.abertura = abertura;
	}

	public String getFechamento() {
		return fechamento;
	}

	public void setFechamento(String fechamento) {
		this.fechamento = fechamento;
	}

	public Estabelecimento getEstabelecimento() {
		return estabelecimento;
	}

	public void setEstabelecimento(Estabelecimento estabelecimento) {
		this.estabelecimento = estabelecimento;
	}

	public static List<String> getDiasSemana() {
		List<String> diasSemana = new ArrayList<>();
		diasSemana.addAll(Arrays.asList("Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sabado", "Domingo"));
		return diasSemana;
	}

}
