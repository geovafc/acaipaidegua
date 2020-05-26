package br.com.acaipaideguaweb.controller.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import br.com.acaipaideguaweb.model.Produto;

public class ProdutoConverter implements Converter<String, Produto>{

	@Override
	public Produto convert(String id) {
		if (!StringUtils.isEmpty(id)) {
			Produto produto = new Produto();
			produto.setId(Long.valueOf(id));
			return produto;
		}
		return null;
	}

}
