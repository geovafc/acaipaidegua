package br.com.acaipaideguaweb.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.acaipaideguaweb.model.Cliente;
import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.ItemVenda;
import br.com.acaipaideguaweb.model.StatusVenda;
import br.com.acaipaideguaweb.model.TabelaPreco;
import br.com.acaipaideguaweb.model.Venda;
import br.com.acaipaideguaweb.repositorio.Clientes;
import br.com.acaipaideguaweb.repositorio.Estabelecimentos;
import br.com.acaipaideguaweb.repositorio.TabelaPrecos;
import br.com.acaipaideguaweb.repositorio.VendaRepository;
import br.com.acaipaideguaweb.repositorio.filter.VendaFilter;
import br.com.acaipaideguaweb.resources.dto.VendaDTO;
import br.com.acaipaideguaweb.resources.dto.mapper.VendaMapper;
import br.com.acaipaideguaweb.service.exceptions.VendaNaoEncontradaExceptions;

@Service
public class VendasService {

	@Autowired
	private VendaRepository vendaRepository;

	@Autowired
	private Estabelecimentos estabelecimentoRepository;

	@Autowired
	private Clientes clienteRepository;

	@Autowired
	private TabelaPrecos tabelaPrecoRepository;

	@Autowired
	private ItemVendaService ivRepository;

	@Autowired
	private Clientes clientesRepository;

	private Cliente c;

	private TabelaPreco tp;

	private ItemVenda iv;

	public List<Venda> findAllByOrderIdDesc() {
		return vendaRepository.findByOrderByIdDesc();
	}

	public Estabelecimento filtrarEstabelecimento(VendaFilter filtro) {
		Long estabelecimento = filtro.getEstabelecimento() == null ? null : filtro.getEstabelecimento();
		return estabelecimentoRepository.findOne(estabelecimento);
	}

	public Cliente filtrarCliente(VendaFilter filtro) {
		Long cliente = filtro.getCliente() == null ? null : filtro.getCliente();
		return clientesRepository.findOne(cliente);
	}

	public List<Cliente> listarClientes() {
		return clientesRepository.findAll();
	}
	
	public List<Estabelecimento> listarEstabelecimento() {
		return estabelecimentoRepository.findAll();
	}

	public Venda salvar(Venda venda) {

		if (venda.getId() == null) {

			venda.setDataVenda(Date.valueOf(LocalDate.now()));

			venda.setStatus(StatusVenda.NOVO);

			vendaRepository.save(venda);

		} else {

			if (venda.getStatus().equals(StatusVenda.ENTREGUE)) {
				venda.setDataHoraEntrega(Date.valueOf(LocalDate.now()));
			}

			venda = vendaRepository.save(venda);
		}

		return venda;
	}

	public Venda salvarFromDTO(VendaDTO vendaDTO) {

		Venda venda = Mappers.getMapper(VendaMapper.class).vendaDTOToVenda(vendaDTO);
		
		if (venda.getValorDesconto()==null){
			venda.setValorDesconto(new BigDecimal(0));
		}

		Cliente cliente = new Cliente();
		cliente.setRefreshedToken(vendaDTO.getCliente().getRefreshedToken());
		cliente.setUid(vendaDTO.getCliente().getUid());
		cliente.setEmail(vendaDTO.getCliente().getEmail());
		cliente.setTelefone(vendaDTO.getCliente().getTelefone());
		cliente.setNome(vendaDTO.getCliente().getNome());
		venda.setCliente(cliente);

		if (venda.getId() == null) {

			venda.setDataVenda(new Date(System.currentTimeMillis()));

			System.out.println(venda.getDataVenda());

			venda.setCliente(verificarSeClienteJaExiste(venda));

			venda.setStatus(StatusVenda.NOVO);

			vendaRepository.save(venda);

			for (int i = 0; i < vendaDTO.getItens_vendas().size(); i++) {

				iv = new ItemVenda();
				iv.setQuantidade(vendaDTO.getItens_vendas().get(i).getQuantidade());
				iv.setVenda(venda);

				tp = tabelaPrecoRepository.findOne(vendaDTO.getItens_vendas().get(i).getTabelaPrecoID());
				iv.setTabelaPreco(tp);

				ivRepository.salvar(iv);

			}

		} else {

			if (venda.getStatus().equals(StatusVenda.ENTREGUE)) {
				venda.setDataHoraEntrega(new Date(System.currentTimeMillis()));
				// venda.setDataHoraEntrega( Date.valueOf(LocalDate.now()));
			}

			venda = vendaRepository.save(venda);
		}

		return venda;
	}

	public Cliente verificarSeClienteJaExiste(Venda venda) {
		System.out.println("aqui1");

		try {

			c = clienteRepository.findByUid(venda.getCliente().getUid());
			System.out.println("aqui2");
			if (c == null) {

				c = new Cliente();

				c.setEmail(venda.getCliente().getEmail());
				c.setRefreshedToken(venda.getCliente().getRefreshedToken());
				c.setUid(venda.getCliente().getUid());
				c.setNome(venda.getCliente().getNome());
				c.setTelefone(venda.getCliente().getTelefone());
				c.setEndereco(venda.getCliente().getEndereco());
				c.setBairro(venda.getCliente().getBairro());

				return clienteRepository.save(c);

			} else {

				if (c.equals(venda.getCliente())) {
					System.out.println("igual");
				} else {
					System.out.println("diferente " + venda.getCliente().getNome());
					c = new Cliente();

					c.setEmail(venda.getCliente().getEmail());
					c.setRefreshedToken(venda.getCliente().getRefreshedToken());
					c.setUid(venda.getCliente().getUid());
					c.setNome(venda.getCliente().getNome());
					c.setTelefone(venda.getCliente().getTelefone());
					c.setEndereco(venda.getCliente().getEndereco());
					c.setBairro(venda.getCliente().getBairro());
					c.setVendas(new ArrayList<>());

					clienteRepository.save(c);

				}

				return c;

			}

		} catch (Exception e) {
			System.out.println("causa " + e.getMessage());
		}
		return c;

	}

	public Venda obterVenda(Long id) {
		Venda venda = vendaRepository.findOne(id);

		if (venda == null) {
			throw new VendaNaoEncontradaExceptions("A venda não pode ser encontrado");
		}

		return venda;
	}

	public List<Venda> obterVendasPorEstabelecimento(long idEstabelecimento) {
		return vendaRepository.obterVendasPorEstabelecimento(idEstabelecimento);
	}

	public List<Venda> obterVendasPorCliente(String uidCliente) {

		return vendaRepository.obterVendasPorCliente(uidCliente);
	}

	public List<Venda> obterVendasPorEstabelecimentoEData(long idEstabelecimento, Date data) {
		return vendaRepository.obterVendasPorEstabelecimentoEDataVenda(idEstabelecimento, data);
	}

	public Date maiorDataVendaPorEstabelecimento(Long estabelecimento) {
		return (Date) vendaRepository.maiorDataVendaPorEstabelecimento(estabelecimento);
	}

	public Venda buscar(Long id) {
		return vendaRepository.findOne(id);
	}

}
