package br.com.acaipaideguaweb.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Perfil;
import br.com.acaipaideguaweb.model.Usuario;
import br.com.acaipaideguaweb.model.UsuarioRole;
import br.com.acaipaideguaweb.repositorio.filter.UsuarioEstabelecimentoFilter;
import br.com.acaipaideguaweb.service.UsuarioService;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ModelAndView todosusuarios(ModelMap model) {

		List<Usuario> usuarios = usuarioService.findAll();

		model.addAttribute("usuarios", usuarios);

		return new ModelAndView("usuario/ListaUsuarios", model);
	}

	@GetMapping(value = "/add")
	public ModelAndView showForm(@ModelAttribute("usuario") Usuario usuario) {
		return new ModelAndView("usuario/CadastroUsuario");
	}

	@GetMapping(value = "/perfil/{id}")
	public ModelAndView addPerfil(@PathVariable("id") Usuario usuario) {
		ModelAndView view = new ModelAndView("usuario/perfil");

		UsuarioRole role = new UsuarioRole();
		role.setUsuario(usuario);

		view.addObject("role", role);

		view.addObject(usuario);

		return view;
	}

	@PostMapping(value = "/save")
	public String save(@Validated Usuario usuario, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "usuario/CadastroUsuario";
		}

		usuario = usuarioService.save(usuario);

		usuarioService.save(usuario);

		UsuarioRole role = new UsuarioRole();

		role.setUsuario(usuario);

		role.setPerfil(Perfil.ROLE_USER);

		usuarioService.saveRole(role);

		attributes.addFlashAttribute("mensagem", "Usuario salvo com Sucesso!");

		return "redirect:/usuarios";
	}

	@ModelAttribute("todosPerfisUsuario")
	public List<Perfil> todosPerfisUsuario() {
		return Arrays.asList(Perfil.values());
	}

	@RequestMapping(value = { "/update/{id}", "/update" }, method = { RequestMethod.GET, RequestMethod.POST })
	public ModelAndView update(@PathVariable("id") Optional<Long> id, @ModelAttribute("usuario") Usuario usuario) {
		ModelAndView view = new ModelAndView();

		if (id.isPresent()) {
			usuario = usuarioService.findById(id.get());
			view.addObject("usuario", usuario);
			view.setViewName("usuario/atualizar");
			return view;
		}

		usuarioService.updateNomeAndEmail(usuario);

		view.setViewName("redirect:/usuarios/perfil/" + usuario.getId());

		return view;
	}

	@RequestMapping(value = { "/update/senha/{id}", "/update/senha" }, method = { RequestMethod.GET,
			RequestMethod.POST })
	public ModelAndView updadeSenha(@PathVariable("id") Optional<Long> id, @ModelAttribute("usuario") Usuario usuario) {
		ModelAndView view = new ModelAndView();

		if (id.isPresent()) {
			usuario = usuarioService.findById(id.get());
			view.addObject("usuario", usuario);
			view.setViewName("usuario/atualizar");
			return view;
		}

		usuarioService.updateSenha(usuario);

		view.setViewName("redirect:/usuarios");

		return view;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		usuarioService.excluir(id);

		attributes.addFlashAttribute("mensagem", "Usuario excluído com Sucesso!");
		return "redirect:/usuarios";
	}

	@GetMapping("/pesquisarEstabelecimento/{id}")
	public ModelAndView pesquisarEstabelecimento(@PathVariable("id") Usuario usuario,
			@ModelAttribute("filtro") UsuarioEstabelecimentoFilter filtro, RedirectAttributes attributes) {

		ModelAndView view = new ModelAndView("usuario/adicionarEstabelecimento");

		if (filtro.getCodigo() != null) {
			Estabelecimento estabelecimento = usuarioService.filtrar(filtro);

			if (estabelecimento.getUsuario() != null) {
				attributes.addFlashAttribute("mensagemAlerta",
						"O estabelecimento já pertence ao usuário: " + estabelecimento.getUsuario().getUsername());

				view.addObject(usuario);

				view.setViewName("redirect:/usuarios/pesquisarEstabelecimento/" + usuario.getId());
			}

			if (usuario.getListaEstabelecimentos().contains(estabelecimento)) {
				attributes.addFlashAttribute("mensagemAlerta", "O estabelecimento ja pertence a lista do usuário");
				view.addObject(usuario);

				view.setViewName("redirect:/usuarios/pesquisarEstabelecimento/" + usuario.getId());
			} else {
				estabelecimento.setUsuario(usuario);
				view.addObject(estabelecimento);
			}
		}

		view.addObject(usuario);
		return view;

	}

	@PostMapping("/addEstabelecimento")
	public ModelAndView addEstabelecimento(Estabelecimento estabelecimento, RedirectAttributes attributes) {

		ModelAndView view = new ModelAndView();

		usuarioService.salvarUsuarioEstabelecimento(estabelecimento);

		attributes.addFlashAttribute("mensagem", "O estabelecimento foi adicionado a lista do usuário");

		view.setViewName("redirect:/usuarios/pesquisarEstabelecimento/" + estabelecimento.getUsuario().getId());

		return view;

	}
}
