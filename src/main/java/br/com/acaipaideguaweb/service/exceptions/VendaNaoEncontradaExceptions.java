package br.com.acaipaideguaweb.service.exceptions;

public class VendaNaoEncontradaExceptions extends RuntimeException{

	private static final long serialVersionUID = 1869300553614629710L;

	public VendaNaoEncontradaExceptions(String mensagem) {
		super(mensagem);
	}

	public VendaNaoEncontradaExceptions(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
