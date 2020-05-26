package br.com.acaipaideguaweb.resources.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.acaipaideguaweb.model.TabelaPreco;
import br.com.acaipaideguaweb.resources.dto.TabelaPrecoDTO;

@Mapper(componentModel = "spring", uses = { ProdutoMapper.class })
public interface TabelaPrecoMapper {
	
    @Mapping(target="nomeProduto", source="tabelaPreco.produto.nome")
	TabelaPrecoDTO tabelaPrecoToTabelaPrecoDTO(TabelaPreco tabelaPreco);

	TabelaPreco TabelaPrecoDTOToTabelaPreco(TabelaPrecoDTO TabelaPrecoTO);

	List<TabelaPrecoDTO> tabelaPrecosToTabelaPrecoDTOs(List<TabelaPreco> tabelaPrecos);

}
