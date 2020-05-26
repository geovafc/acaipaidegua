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
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Operadora;
import br.com.acaipaideguaweb.model.Telefone;
import br.com.acaipaideguaweb.repositorio.Telefones;

@Controller
@RequestMapping("/telefones")
public class TelefoneController {

	@Autowired
	private Telefones telefones;


	@RequestMapping("/add/{id}")
	public ModelAndView adicionar(@PathVariable("id") Estabelecimento estabelecimento){
		Telefone telefone = new Telefone();
		telefone.setEstabelecimento(estabelecimento);
		System.out.println(estabelecimento.getNome());
		ModelAndView view = new ModelAndView("telefone/CadastroTelefone");
		view.addObject(telefone);
		return view;
	}
	
	@RequestMapping(value="/salvar",method = RequestMethod.POST)
	public String salvar(@Validated Telefone telefone, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return "telefone/CadastroTelefone";
		}
		
		try {
			telefones.save(telefone);
			attributes.addFlashAttribute("mensagem", "Telefone salvo com Sucesso!");
			return "redirect:/estabelecimentos/detalhe/"+ telefone.getEstabelecimento().getId();
			
		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "telefone/CadastroTelefone";
		}
	}
	
	@RequestMapping(value="/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id")Telefone telefone) {
		
		ModelAndView view = new ModelAndView("telefone/CadastroTelefone");
		view.addObject(telefone);
		return view;
		
	}
	
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable("id")Telefone telefone) {
		
		telefones.delete(telefone);
		return "redirect:/estabelecimentos/detalhe/"+ telefone.getEstabelecimento().getId();
		
	}
	
	@ModelAttribute("todasOperadoras")
	public List<Operadora> todasOperadores() {
		return Arrays.asList(Operadora.values());
	}
	
}
