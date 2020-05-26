package br.com.acaipaideguaweb.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.acaipaideguaweb.model.Atualizacao;
import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.StatusEstabelecimento;
import br.com.acaipaideguaweb.model.TabelaPreco;
import br.com.acaipaideguaweb.model.Telefone;

import br.com.acaipaideguaweb.repositorio.AtualizacaoRepository;
import br.com.acaipaideguaweb.repositorio.Estabelecimentos;
import br.com.acaipaideguaweb.repositorio.TabelaPrecos;
import br.com.acaipaideguaweb.repositorio.Telefones;
import br.com.acaipaideguaweb.repositorio.filter.EstabelecimentoFilter;

@Service
public class EstabelecimentoService {

	@Autowired
	private Estabelecimentos estabelecimentos;
	
	@Autowired
	private AtualizacaoRepository atualizacaoRepository;

	@Autowired
	private Telefones telefones;

	@Autowired
	private TabelaPrecos precos;

	public void salvar(Estabelecimento estabelecimento) {
		try {
			if (estabelecimento.getId() == null) {
				estabelecimento.setDataCadastro(Date.valueOf(LocalDate.now()));
				estabelecimento.setComSelo(Boolean.TRUE);
			}
			estabelecimentos.save(estabelecimento);
			
			atualizarServidor();

		} catch (DataIntegrityViolationException e) {
			throw new IllegalArgumentException("Formato de data inválido");
		}
	}

	public void excluir(Long id) {
		estabelecimentos.delete(id);
		atualizarServidor();

	}

	public String receber(Long id) {
		Estabelecimento estabelecimento = estabelecimentos.findOne(id);
		estabelecimento.setStatus(StatusEstabelecimento.RECEBIDO);
		estabelecimentos.save(estabelecimento);
		atualizarServidor();

		return StatusEstabelecimento.RECEBIDO.getDescricao();

	}
	
	public Page<Estabelecimento> filtrar(EstabelecimentoFilter filtro, String userName, int page, int size) {

		String nome = filtro.getNome() == null ? "%" : filtro.getNome();
		 
		Pageable pageable = new PageRequest(page, size);
		

		if (!userName.equals("")) {
			return (Page<Estabelecimento>) estabelecimentos.findByNomeContainingAndUsuarioUsernameOrderByIdAsc(nome, userName, pageable);
		} else {
			return (Page<Estabelecimento>) estabelecimentos.findByNomeContainingOrderByIdAsc(nome, pageable);
		}

	}

	public List<Estabelecimento> comSelos(Boolean selo) {
		return estabelecimentos.findByComSelo(selo);
	}

	public List<Estabelecimento> listarTodos() {
		return estabelecimentos.findAll();
	}

	public List<Telefone> findByTelefoneEstabelecimento(Estabelecimento estabelecimento) {
		return telefones.findByEstabelecimento(estabelecimento);
	}

	public List<TabelaPreco> findByTabelaPrecoEstabelecimento(Estabelecimento estabelecimento) {
		return precos.findByEstabelecimento(estabelecimento);
	}

	public Estabelecimento buscar(Long id) {
		return estabelecimentos.findOne(id);
	}

	public void updateUsuario(Long user, Long id) {
		// estabelecimentos.updateUsuario(user, id);
	}
	
	public void atualizarServidor(){
		Atualizacao atualizacao = atualizacaoRepository.findOne(new Long(1));
		atualizacao.setDataAtualizacao(new Date(System.currentTimeMillis()));
		atualizacaoRepository.save(atualizacao);
	}
}
