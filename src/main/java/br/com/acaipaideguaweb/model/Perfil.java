package br.com.acaipaideguaweb.model;

public enum Perfil {

	ROLE_ADMIN("Administrador"), ROLE_USER("Usuário");

	private String descricao;

	Perfil(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
