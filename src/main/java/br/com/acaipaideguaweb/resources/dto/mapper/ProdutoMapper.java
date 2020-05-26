package br.com.acaipaideguaweb.resources.dto.mapper;


import org.mapstruct.*;

import br.com.acaipaideguaweb.model.Produto;
import br.com.acaipaideguaweb.resources.dto.ProdutoDTO;

import java.util.List;
import java.util.Set;

/**
 * Mapper for the entity Produto and its DTO ProdutoDTO.
 */
@Mapper(componentModel = "spring")
public interface ProdutoMapper {

    ProdutoDTO produtoToProdutoDTO(Produto produto);

    List<ProdutoDTO> produtosToProdutoDTOs(List<Produto> produtos);

    List<ProdutoDTO> produtosSetToProdutoDTOlist (Set<Produto> produtos);

    Produto produtoDTOToProduto(ProdutoDTO produtoDTO);

    List<Produto> produtoDTOsToProdutos(List<ProdutoDTO> produtoDTOs);




}
