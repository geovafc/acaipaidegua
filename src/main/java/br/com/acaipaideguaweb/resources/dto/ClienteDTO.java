package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;


public class ClienteDTO implements Serializable{
		
	private String uid;
	
	private String nome;

	private String email;

	private String telefone;

	private String refreshedToken;

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getRefreshedToken() {
		return refreshedToken;
	}

	public void setRefreshedToken(String refreshedToken) {
		this.refreshedToken = refreshedToken;
	}
	
	
	
	


}
