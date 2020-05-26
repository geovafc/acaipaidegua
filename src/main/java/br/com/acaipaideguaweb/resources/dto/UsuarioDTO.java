package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class UsuarioDTO implements Serializable {
	
	private long id;
	
	private String username;

	List<Map> estabelecimentos;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Map> getEstabelecimentos() {
		return estabelecimentos;
	}

	public void setEstabelecimentos(List<Map> estabelecimentos) {
		this.estabelecimentos = estabelecimentos;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
																																																							


	
	

}
