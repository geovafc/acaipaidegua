package br.com.acaipaideguaweb.model;

public enum StatusEstabelecimento {

	PENDENTE("Pendente"), RECEBIDO("Recebido"), EMANALISE("Em análise");

	private String descricao;

	StatusEstabelecimento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
