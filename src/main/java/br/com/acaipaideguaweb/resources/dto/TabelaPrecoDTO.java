package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;



public class TabelaPrecoDTO implements Serializable{
	

	private Long id;

	private BigDecimal preco;
	
	private String nomeProduto;



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	
	

	
	


}
