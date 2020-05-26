package br.com.acaipaideguaweb.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.StatusEstabelecimento;
import br.com.acaipaideguaweb.repositorio.filter.EstabelecimentoFilter;
import br.com.acaipaideguaweb.service.EstabelecimentoService;

@Controller
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

	private static final String CADASTRO_VIEW = "estabelecimento/CadastroEstabelecimento";

	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@RequestMapping(value = "/novo", method = RequestMethod.GET)
	public ModelAndView novo() {
		ModelAndView view = new ModelAndView(CADASTRO_VIEW);
		view.addObject(new Estabelecimento());
		return view;
	}

	@RequestMapping(value = "/novo", method = RequestMethod.POST)
	public String salvar(@Validated Estabelecimento estabelecimento, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return CADASTRO_VIEW;
		}
		try {

			estabelecimentoService.salvar(estabelecimento);

			attributes.addFlashAttribute("mensagem", "Estabelecimento salvo com Sucesso!");

			return "redirect:/estabelecimentos";

		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataCadastro", null, e.getMessage());
			return CADASTRO_VIEW;
		}
	}
	
	@GetMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") EstabelecimentoFilter filtro) {
		
		String userName = "";
		
		if (SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
			userName = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		ModelAndView view = new ModelAndView("estabelecimento/PesquisaEstabelecimento");
		
		Page<Estabelecimento> page = estabelecimentoService.filtrar(filtro, userName, 0, 8);
		
		int current = page.getNumber() + 1;
		
		int begin = 1;
		
		int end = page.getTotalPages();
		
		view.addObject("page", page);
		
		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);
		
		//List<Estabelecimento> todosEstabelecimentos = estabelecimentoService.filtrar(filtro, userName);
		//view.addObject("estabelecimentos", todosEstabelecimentos);

		return view;
	}
	
	@GetMapping(value = "/page/{page}")
	public ModelAndView pageProdutos(@PathVariable("page") Integer pagina,
									@ModelAttribute("filtro") EstabelecimentoFilter filtro){
		
		String userName = "";
		
		if (SecurityContextHolder.getContext().getAuthentication()
				.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER"))){
			userName = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		
		ModelAndView view = new ModelAndView("estabelecimento/PesquisaEstabelecimento");
		
		Page<Estabelecimento> page = estabelecimentoService.filtrar(filtro, userName,pagina, 8);
		
		int current = page.getNumber() + 1;
		
		int begin = 1;
		
		int end = page.getTotalPages();

		view.addObject("page", page);
		
		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);
		
		return view;
	}
	

	@RequestMapping("/selo")
	public ModelAndView pesquisarComSelo() {
		List<Estabelecimento> todosEstabelecimentosComSelo = estabelecimentoService.comSelos(true);
		ModelAndView view = new ModelAndView("estabelecimento/PesquisaEstabelecimentocomselo");
		view.addObject("estabelecimentos", todosEstabelecimentosComSelo);

		return view;
	}

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Long id) {
		// Em vez de passar um long e em seguida buscar o objeto atravez do
		// findOne(Long)
		// Desse modo como foi feito o spring ja entende que tem buscar o objeto
		// de acordo com o id(id)

		Estabelecimento estabelecimento = estabelecimentoService.buscar(id);
		ModelAndView view = new ModelAndView(CADASTRO_VIEW);
		view.addObject(estabelecimento);
		return view;
	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhes(@PathVariable("id") Long id) {

		Estabelecimento estabelecimento = estabelecimentoService.buscar(id);

		ModelAndView view = new ModelAndView("estabelecimento/DetalhesEstabelecimento");

		view.addObject(estabelecimento);

		return view;

	}

	@GetMapping("/editarEstabelecimentoPeloUsuario/{id}")
	public ModelAndView editarEstabelecimentoPeloUsuario(@PathVariable("id") Estabelecimento estabelecimento) {
		// Em vez de passar um long e em seguida buscar o objeto atravez do
		// findOne(Long)
		// Desse modo como foi feito o spring ja entende que tem buscar o objeto
		// de acordo com o id(id)
		ModelAndView view = new ModelAndView("estabelecimento/EditarEstabelecimentoUsuario");
		view.addObject(estabelecimento);
		return view;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		estabelecimentoService.excluir(id);

		attributes.addFlashAttribute("mensagem", "Estabelecimento excluído com Sucesso!");
		return "redirect:/estabelecimentos";
	}

	@RequestMapping(value = "/{id}/receber", method = RequestMethod.PUT)
	public @ResponseBody String receber(@PathVariable Long id) {
		return estabelecimentoService.receber(id);
	}

	@ModelAttribute("todosStatusEstabelecimento")
	public List<StatusEstabelecimento> todosStatusEstabelecimento() {
		return Arrays.asList(StatusEstabelecimento.values());
	}

	@RequestMapping("/localizacao")
	public ModelAndView todosEstabelecimento() {
		// List<Estabelecimento> todosEstabelecimentos =
		// estabelecimentoService.findAll();
		// model.addAttribute("todosEstabelecimentos", todosEstabelecimentos);
		return new ModelAndView("estabelecimento/Localizacao");
	}

}
