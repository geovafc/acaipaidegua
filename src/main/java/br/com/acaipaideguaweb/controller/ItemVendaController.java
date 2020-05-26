package br.com.acaipaideguaweb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.acaipaideguaweb.model.ItemVenda;
import br.com.acaipaideguaweb.service.ItemVendaService;

@Controller
@RequestMapping("/item")
public class ItemVendaController {
	
	@Autowired
	private ItemVendaService service;
	
	
	@PostMapping
	public ModelAndView salvar(ItemVenda item) {
		ModelAndView view = new ModelAndView("redirect:/vendas");
		
		ItemVenda itemRec = service.salvar(item);
		
//		view.addObject("itens", itemRec.getVenda().getItem_venda());
//		
//		ItemVenda itemNovo = new ItemVenda();
//		
//		itemNovo.setVenda(itemRec.getVenda());
//		
//		view.addObject("item", itemNovo);
		
		return view;
	}

}
