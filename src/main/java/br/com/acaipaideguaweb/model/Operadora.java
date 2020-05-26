package br.com.acaipaideguaweb.model;

public enum Operadora {

	VIVO("Vivo"),
	CLARO("Claro"),
	OI("Oi"),
	TIM("Tim");

	private String descricao;

	Operadora(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
