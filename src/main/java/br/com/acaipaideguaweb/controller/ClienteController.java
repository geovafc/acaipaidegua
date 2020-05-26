package br.com.acaipaideguaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.acaipaideguaweb.model.Cliente;
import br.com.acaipaideguaweb.model.Produto;
import br.com.acaipaideguaweb.repositorio.Clientes;

@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	private static final String CLIENTES_VIEW = "cliente/clientes";
	
	@Autowired
	private Clientes clientes;
	
	@GetMapping
	public ModelAndView listar() {
		ModelAndView view = new ModelAndView(CLIENTES_VIEW);

		view.addObject("clientes", clientes.findAll());

		view.addObject(new Cliente());

		return view;
	}
	
	@PostMapping
	public ModelAndView salvar(@Validated Cliente cliente, Errors errors, RedirectAttributes attributes) {
		ModelAndView view = new ModelAndView();
		
		if (errors.hasErrors()) {
			view.addObject("clientes", clientes.findAll());
			view.setViewName(CLIENTES_VIEW);
			return view;
		}

		try {

			this.clientes.save(cliente);

			attributes.addFlashAttribute("mensagem", "Cliente salvo com Sucesso!");
			
			view.setViewName("redirect:/clientes");

			return view;

		} catch (IllegalArgumentException e) {
			return new ModelAndView("redirect:/clientes");
		}

	}

}
