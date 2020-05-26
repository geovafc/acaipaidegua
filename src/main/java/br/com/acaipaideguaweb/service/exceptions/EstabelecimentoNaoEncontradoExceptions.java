package br.com.acaipaideguaweb.service.exceptions;

public class EstabelecimentoNaoEncontradoExceptions extends RuntimeException{

	private static final long serialVersionUID = 1869300553614629710L;

	public EstabelecimentoNaoEncontradoExceptions(String mensagem) {
		super(mensagem);
	}

	public EstabelecimentoNaoEncontradoExceptions(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
