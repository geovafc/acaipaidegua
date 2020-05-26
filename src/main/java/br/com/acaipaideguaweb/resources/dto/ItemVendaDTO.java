package br.com.acaipaideguaweb.resources.dto;

import java.io.Serializable;
import java.math.BigDecimal;


public class ItemVendaDTO implements Serializable{
	
	private Long id;

	private Float quantidade;

	private Long tabelaPrecoID;

	private Long vendaID;
	
	private String nome;

	private BigDecimal preco;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Float getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Float quantidade) {
		this.quantidade = quantidade;
	}

	public Long getTabelaPrecoID() {
		return tabelaPrecoID;
	}

	public void setTabelaPrecoID(Long tabelaPrecoID) {
		this.tabelaPrecoID = tabelaPrecoID;
	}

	public Long getVendaID() {
		return vendaID;
	}

	public void setVendaID(Long vendaID) {
		this.vendaID = vendaID;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}


	
	
	
	

}
