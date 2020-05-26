package br.com.acaipaideguaweb.handler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import br.com.acaipaideguaweb.model.DetalhesErro;
import br.com.acaipaideguaweb.service.exceptions.EstabelecimentoNaoEncontradoExceptions;
import br.com.acaipaideguaweb.service.exceptions.VendaNaoEncontradaExceptions;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler(EstabelecimentoNaoEncontradoExceptions.class)
	public ResponseEntity<DetalhesErro> handleEstabelecimentoNaoEncontradoExceptions
						(EstabelecimentoNaoEncontradoExceptions e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("O Estabelecimento não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.acaipaideguaweb.com.br/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	@ExceptionHandler(VendaNaoEncontradaExceptions.class)
	public ResponseEntity<DetalhesErro> handleVendaNaoEncontradaExceptions
						(VendaNaoEncontradaExceptions v, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(404l);
		erro.setTitulo("A venda não pôde ser encontrado");
		erro.setMensagemDesenvolvedor("http://erros.acaipaideguaweb.com.br/404");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
	}
	
	
	
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<DetalhesErro> handleDataIntegrityViolationException
	(DataIntegrityViolationException e, HttpServletRequest request){
		
		DetalhesErro erro = new DetalhesErro();
		erro.setStatus(400l);
		erro.setTitulo("Requisição Inválida");
		erro.setMensagemDesenvolvedor("http://erros.acaipaideguaweb.com.br/400");
		erro.setTimestamp(System.currentTimeMillis());
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
	}
}
