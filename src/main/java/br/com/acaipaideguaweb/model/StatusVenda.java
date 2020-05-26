package br.com.acaipaideguaweb.model;

public enum StatusVenda {

	NOVO("Novo"), 
	ACEITO("Aceito"), 
	ACAMINHO("A Caminho"),
	CONCLUIDO("Concluído"),
	CANCELADO("Cancelado"), 
	ENTREGUE("Entregue");

	private String descricao;

	StatusVenda(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
