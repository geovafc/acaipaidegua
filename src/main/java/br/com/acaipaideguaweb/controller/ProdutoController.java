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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Produto;
import br.com.acaipaideguaweb.service.ProdutoService;

@Controller
@RequestMapping("/produtos")
public class ProdutoController {

	private static final String PRODUTOS_VIEW = "produto/produtos";

	@Autowired
	private ProdutoService produtoService;
	
	@GetMapping(value = "/page/{page}")
	public ModelAndView pageProdutos(@PathVariable("page") Integer pagina,
									@ModelAttribute("produto") Produto produto){
		ModelAndView view = new ModelAndView(PRODUTOS_VIEW);
		
		Page<Produto> page = produtoService.findByPagination(pagina, 5);
		
		int current = page.getNumber() + 1;
		
		int begin = 1;
		
		int end = page.getTotalPages();

		view.addObject("page", page);
		
		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);
		
		return view;
	}

	@GetMapping
	public ModelAndView listar() {
		ModelAndView view = new ModelAndView(PRODUTOS_VIEW);
		
		Page<Produto> page = produtoService.findByPagination(0, 5);

		//view.addObject("produtos", produtoService.listar());
		
		int current = page.getNumber() + 1;
		
		int begin = 1;
		
		int end = page.getTotalPages();
		
		view.addObject("page", page);
		
		view.addObject("begin", begin);
		view.addObject("end", end);
		view.addObject("current", current);
		view.addObject(new Produto());

		return view;
	}

	@PostMapping
	public ModelAndView salvar(@Validated Produto produto, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return listar();
		}

		try {

			this.produtoService.salvar(produto);

			attributes.addFlashAttribute("mensagem", "Produto salvo com Sucesso!");

			return new ModelAndView("redirect:/produtos");

		} catch (IllegalArgumentException e) {
			return new ModelAndView("redirect:/produtos");
		}

	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long id, RedirectAttributes attributes) {

		produtoService.excluir(id);

		attributes.addFlashAttribute("mensagem", "Produto excluído com Sucesso!");
		return "redirect:/produtos";
	}

	@GetMapping("{id}")
	public ModelAndView edicao(@PathVariable("id") Produto produto) {
		// Em vez de passar um long e em seguida buscar o objeto atravez do
		// findOne(Long)
		// Desse modo como foi feito o spring ja entende que tem buscar o objeto
		// de acordo com o id(id)

		ModelAndView view = new ModelAndView(PRODUTOS_VIEW);
		
		Page<Produto> page = produtoService.findByPagination(0, 3);
		//view.addObject("produtos", produtoService.listar());
		view.addObject("page", page);
		view.addObject(produto);
		return view;
	}

}
