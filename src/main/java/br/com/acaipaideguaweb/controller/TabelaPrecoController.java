package br.com.acaipaideguaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.TabelaPreco;
import br.com.acaipaideguaweb.repositorio.TabelaPrecos;
import br.com.acaipaideguaweb.service.ProdutoService;

@Controller
@RequestMapping("/tabelas")
public class TabelaPrecoController {

	@Autowired
	private TabelaPrecos precos;

	@Autowired
	private ProdutoService produtos;

	@RequestMapping("/add/{id}")
	public ModelAndView adicionar(@PathVariable("id") Estabelecimento estabelecimento) {
		ModelAndView view = new ModelAndView("tabelaPreco/CadastroPrecos");
		TabelaPreco tabelaPreco = new TabelaPreco();
		tabelaPreco.setEstabelecimento(estabelecimento);
		view.addObject("produtos", produtos.listar());
		view.addObject(tabelaPreco);
		return view;
	}

	@RequestMapping(value = "/salvar", method = RequestMethod.POST)
	public String salvar(@Validated TabelaPreco tabelaPreco, Errors errors, RedirectAttributes attributes) {

		if (errors.hasErrors()) {
			return "tabelaPreco/CadastroPrecos";
		}

		try {
			precos.save(tabelaPreco);
			attributes.addFlashAttribute("mensagem", "Produto salvo com Sucesso!");
			return "redirect:/estabelecimentos/detalhe/" + tabelaPreco.getEstabelecimento().getId();

		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "tabelaPreco/CadastroPrecos";
		}

	}

	@RequestMapping(value = "/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id") TabelaPreco tabelapreco) {

		ModelAndView view = new ModelAndView("tabelaPreco/CadastroPrecos");
		view.addObject("produtos", produtos.listar());
		view.addObject(tabelapreco);
		return view;

	}

	@RequestMapping(value = "/excluir/{id}")
	public String excluir(@PathVariable("id") TabelaPreco tabelapreco) {

		precos.delete(tabelapreco);
		return "redirect:/estabelecimentos/detalhe/" + tabelapreco.getEstabelecimento().getId();

	}

}
