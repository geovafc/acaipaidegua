package br.com.acaipaideguaweb.model;

public enum TipoHorario {

	COMERCIAL("Comercial"), DELIVERY("Delivery");

	private String descricao;

	TipoHorario(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
