package br.com.acaipaideguaweb.resources.dto.mapper;


import org.mapstruct.*;

import br.com.acaipaideguaweb.model.Telefone;
import br.com.acaipaideguaweb.resources.dto.TelefoneDTO;

import java.util.List;

@Mapper(componentModel = "spring", uses = {})
public interface TelefoneMapper {

    TelefoneDTO telefoneToTelefoneDTO(Telefone telefone);

    Telefone telefoneDTOToTelefone(TelefoneDTO telefoneDTO);
    
    List<TelefoneDTO> telefonesToTelefoneDTOs(List<Telefone> Telefones);



}
