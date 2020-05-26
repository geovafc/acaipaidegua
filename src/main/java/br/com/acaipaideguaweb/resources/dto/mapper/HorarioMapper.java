package br.com.acaipaideguaweb.resources.dto.mapper;


import br.com.acaipaideguaweb.model.Horario;
import br.com.acaipaideguaweb.resources.dto.HorarioDTO;

import org.mapstruct.*;
import java.util.List;


@Mapper(componentModel = "spring", uses = {})
public interface HorarioMapper {

    HorarioDTO horarioToHorarioDTO(Horario horario);

    List<HorarioDTO> horariosToHorarioDTOs(List<Horario> horarios);

    Horario horarioDTOToHorario(HorarioDTO horarioDTO);

    List<Horario> horarioDTOsToHorarios(List<HorarioDTO> horarioDTOs);

}
