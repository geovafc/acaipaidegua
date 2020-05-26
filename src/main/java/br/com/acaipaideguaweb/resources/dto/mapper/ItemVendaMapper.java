package br.com.acaipaideguaweb.resources.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.acaipaideguaweb.model.ItemVenda;
import br.com.acaipaideguaweb.resources.dto.ItemVendaDTO;

@Mapper(componentModel = "spring", uses = {TabelaPrecoMapper.class, VendaMapper.class, ProdutoMapper.class})
public interface ItemVendaMapper {
	

	      @Mapping(target="nome", source="itemVenda.tabelaPreco.produto.nome")
	      @Mapping(target="preco", source="itemVenda.tabelaPreco.preco")
	      @Mapping(target="tabelaPrecoID", source="itemVenda.tabelaPreco.id")
	      @Mapping(target="vendaID", source="itemVenda.venda.id")
	ItemVendaDTO ItemVendaToItemVendaDTO(ItemVenda itemVenda);

	ItemVenda ItemVendaDTOToItemVenda(ItemVendaDTO itemVendaDTO);
	
	List<ItemVendaDTO> item_vendaToItensvendaDTOs(List<ItemVenda> Itensvenda);
	
	List<ItemVenda> item_vendaDTOsToItensvenda(List<ItemVendaDTO> ItensvendaDTO);

}
