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
import br.com.acaipaideguaweb.model.Horario;
import br.com.acaipaideguaweb.model.Operadora;
import br.com.acaipaideguaweb.model.TipoHorario;
import br.com.acaipaideguaweb.repositorio.Horarios;

@Controller
@RequestMapping("/horarios")
public class HorarioController {

	@Autowired
	private Horarios horarios;

	@RequestMapping("/add/{id}")
	public ModelAndView adicionar(@PathVariable("id") Estabelecimento estabelecimento){
		Horario horario= new Horario();
		horario.setEstabelecimento(estabelecimento);
		ModelAndView view = new ModelAndView("horario/CadastroHorario");
		view.addObject(horario);
		return view;
	}
	
	@RequestMapping(value="/salvar",method = RequestMethod.POST)
	public String salvar(@Validated Horario horario, Errors errors, RedirectAttributes attributes) {
		
		if (errors.hasErrors()) {
			return "horario/CadastroHorario";
		}
		
		try {
			horarios.save(horario);
			attributes.addFlashAttribute("mensagem", "Horário salvo com Sucesso!");
			return "redirect:/estabelecimentos/detalhe/"+ horario.getEstabelecimento().getId();
			
		} catch (IllegalArgumentException e) {
			errors.rejectValue(null, e.getMessage());
			return "horario/CadastroHorario";
		}
	}
	
	@RequestMapping(value="/editar/{id}")
	public ModelAndView preEditar(@PathVariable("id")Horario horario) {
		
		ModelAndView view = new ModelAndView("horario/CadastroHorario");
		view.addObject(horario);
		return view;
		
	}
	
	@RequestMapping(value="/excluir/{id}")
	public String excluir(@PathVariable("id")Horario horario) {
		
		horarios.delete(horario);
		return "redirect:/estabelecimentos/detalhe/"+ horario.getEstabelecimento().getId();
		
	}
	
	@ModelAttribute("todosTiposHorario")
	public List<TipoHorario> todasOperadores() {
		return Arrays.asList(TipoHorario.values());
	}
	
	@ModelAttribute("diasSemanas")
	public List<String> diasSemanas() {
		return Horario.getDiasSemana();
	}
	
}
