package br.com.acaipaideguaweb.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acaipaideguaweb.model.Precadastro;
import br.com.acaipaideguaweb.repositorio.PreCadastroRepository;

@RestController
@RequestMapping("/resources/precadastro")
public class PreCadastroResources {

	@Autowired
	private PreCadastroRepository precadastroRepository;

	@GetMapping
	public ResponseEntity<List<Precadastro>> listarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(precadastroRepository.findAll());
	}

}
