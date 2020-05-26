package br.com.acaipaideguaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Precadastro;
import br.com.acaipaideguaweb.repositorio.filter.PreCadastroFilter;
import br.com.acaipaideguaweb.service.PreCadastroService;

@Controller
@RequestMapping("/pre-cadastro")
public class PrecCadastroController {

	private static final String PRE_CADASTRO_VIEW = "pre-cadastro/preCadastro";

	@Autowired
	private PreCadastroService repository;

	@GetMapping
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(PRE_CADASTRO_VIEW);
		view.addObject(new Precadastro());
		return view;
	}

	@PostMapping
	public String salvar(@Validated Precadastro precadastro, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return PRE_CADASTRO_VIEW;
		}
		try {

			repository.salvar(precadastro);

			attributes.addFlashAttribute("mensagem", "Estabelecimento salvo com Sucesso!");

			return "redirect:/pre-cadastro";

		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataCadastro", null, e.getMessage());
			return PRE_CADASTRO_VIEW;
		}
	}

	@GetMapping("/pesquisar")
	public ModelAndView pesquisar(@ModelAttribute("filtro") PreCadastroFilter filtro) {

		ModelAndView view = new ModelAndView("pre-cadastro/listarPrecadastro");
		Page<Precadastro> page = null;

		page = repository.filtrar(0, 5);

		int current = page.getNumber() + 1;

		int begin = 1;

		int end = page.getTotalPages();

		view.addObject("page", page);

		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);

		return view;
	}
	
	@GetMapping("/pesquisar/bairro")
	public ModelAndView pesquisarBairro(@ModelAttribute("filtro") PreCadastroFilter filtro) {
		
		ModelAndView view = new ModelAndView("pre-cadastro/listarPrecadastro");
		Page<Precadastro> page = null;
		
		page = repository.filtrarPorBairro(filtro, 0, 5);
		
		int current = page.getNumber() + 1;
		
		int begin = 1;
		
		int end = page.getTotalPages();
		
		view.addObject("page", page);
		
		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);
		
		return view;
	}

	@GetMapping("/pesquisar/nome")
	public ModelAndView pesquisarNome(@ModelAttribute("filtro") PreCadastroFilter filtro) {

		ModelAndView view = new ModelAndView("pre-cadastro/listarPrecadastro");
		Page<Precadastro> page = null;


			page = repository.filtrarPorNome(filtro, 0, 5);

		int current = page.getNumber() + 1;

		int begin = 1;

		int end = page.getTotalPages();

		view.addObject("page", page);

		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);

		return view;
	}

	@GetMapping(value = "/page/{page}")
	public ModelAndView paginas(@PathVariable("page") Integer pagina,
			@ModelAttribute("filtro") PreCadastroFilter filtro) {

		ModelAndView view = new ModelAndView("pre-cadastro/listarPrecadastro");

		Page<Precadastro> page = repository.filtrar(pagina, 5);

		int current = page.getNumber() + 1;

		int begin = 1;

		int end = page.getTotalPages();

		view.addObject("page", page);

		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);

		return view;
	}

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Precadastro precadastro) {

		ModelAndView view = new ModelAndView(PRE_CADASTRO_VIEW);
		view.addObject(precadastro);
		return view;
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhes(@PathVariable("id") Precadastro precadastro) {

		ModelAndView view = new ModelAndView("pre-cadastro/detalhesPreCadastro");

		view.addObject(precadastro);

		return view;

	}

}
