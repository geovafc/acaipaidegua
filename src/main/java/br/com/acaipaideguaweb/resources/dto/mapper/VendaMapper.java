package br.com.acaipaideguaweb.resources.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueMappingStrategy;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Venda;
import br.com.acaipaideguaweb.resources.dto.VendaDTO;

@Mapper(componentModel = "spring",nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT , uses = { ClienteMapper.class, ItemVendaMapper.class,EstabelecimentoMapper.class})
public interface VendaMapper {
	
	
    @Mapping(target="nomeEstabelecimento", source="venda.estabelecimento.nome")
    @Mapping(target="estabelecimentoId", source="venda.estabelecimento.id")
    @Mapping(target = "itens_vendas", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    VendaDTO vendaToVendaDTO(Venda venda);
    
    
    @Mapping(target = "estabelecimento", source = "estabelecimentoId")
    @Mapping(target = "itens_vendas", ignore = true)
    @Mapping(target = "cliente", ignore = true)
    Venda vendaDTOToVenda(VendaDTO vendaDTO);
    
    
    List<VendaDTO> vendasTovendaDTOs(List<Venda> vendas);
    
    default Estabelecimento estabelecimentoFromId(Long id) {
        if (id == null) {
            return null;
        }
        Estabelecimento estabelecimento = new Estabelecimento();
        estabelecimento.setId(id);
        return estabelecimento;
    }

}
