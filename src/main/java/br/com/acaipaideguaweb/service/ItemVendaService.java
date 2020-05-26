package br.com.acaipaideguaweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acaipaideguaweb.model.ItemVenda;
import br.com.acaipaideguaweb.repositorio.ItensVendasRepository;

@Service
public class ItemVendaService {
	
	@Autowired
	private ItensVendasRepository itens;
	
	public ItemVenda salvar(ItemVenda item){
		return itens.save(item);
	}
	
	

}
