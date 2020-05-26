package br.com.acaipaideguaweb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Perfil;
import br.com.acaipaideguaweb.model.UsuarioRole;
import br.com.acaipaideguaweb.repositorio.UsuarioRoleRepository;

@Controller
@RequestMapping("/perfil")
public class PerfilController {

	@Autowired
	private UsuarioRoleRepository repository;

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Validated UsuarioRole role, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "usuarios";
		}

		try {
			repository.save(role);
			attributes.addFlashAttribute("mensagem", "Perfil salvo com Sucesso!");
			return "redirect:/usuarios/perfil/" + role.getUsuario().getId();

		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "usuarios";
		}
	}

	@ModelAttribute("todosPerfisUsuario")
	public List<Perfil> todosPerfisUsuario() {
		return Arrays.asList(Perfil.values());
	}

	@RequestMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable("id") UsuarioRole role) {

		repository.delete(role);
		return "redirect:/usuarios/perfil/" + role.getUsuario().getId();

	}
}