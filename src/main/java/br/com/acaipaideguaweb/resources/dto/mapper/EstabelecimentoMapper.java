package br.com.acaipaideguaweb.resources.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.resources.dto.EstabelecimentoDTO;

@Mapper(componentModel = "spring")
public interface EstabelecimentoMapper {
	
	
    @Mapping(target = "telefones", ignore = true)
    @Mapping(target = "listaPrecos", ignore = true)
    @Mapping(target = "horarios", ignore = true)
	EstabelecimentoDTO estabelecimentoToEstabelecimentoDTO(Estabelecimento estabelecimento);
    
	@Mapping(target = "telefones", ignore = true)
    @Mapping(target = "listaPrecos", ignore = true)
    @Mapping(target = "horarios", ignore = true)
    Estabelecimento estabelecimentoDTOToEstabelecimento(EstabelecimentoDTO estabelecimentoDTO);
    
    List<EstabelecimentoDTO> estabelecimentosToEstabelecimentoDTOs(List<Estabelecimento> estabelecimentos);

}
