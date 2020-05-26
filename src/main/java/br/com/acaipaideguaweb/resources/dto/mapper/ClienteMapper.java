package br.com.acaipaideguaweb.resources.dto.mapper;

import org.mapstruct.Mapper;

import br.com.acaipaideguaweb.model.Cliente;
import br.com.acaipaideguaweb.resources.dto.ClienteDTO;



@Mapper(componentModel = "spring",  uses = {})
public interface ClienteMapper {
	
	ClienteDTO clienteToClienteDTO(Cliente cliente);

	Cliente clienteDTOToCliente(ClienteDTO clienteDTO);

}
