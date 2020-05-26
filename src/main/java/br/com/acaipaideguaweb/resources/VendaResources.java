package br.com.acaipaideguaweb.resources;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.validation.Valid;
import javax.validation.constraints.Null;

import org.json.JSONException;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

import br.com.acaipaideguaweb.fcm.notificacao.NotificarPedidoEstabelecimento;
import br.com.acaipaideguaweb.fcm.notificacao.NotificarStatusPedidoCliente;
import br.com.acaipaideguaweb.model.Cliente;
import br.com.acaipaideguaweb.model.Venda;
import br.com.acaipaideguaweb.repositorio.ItensVendasRepository;
import br.com.acaipaideguaweb.resources.dto.VendaDTO;
import br.com.acaipaideguaweb.resources.dto.mapper.ClienteMapper;
import br.com.acaipaideguaweb.resources.dto.mapper.ItemVendaMapper;
import br.com.acaipaideguaweb.resources.dto.mapper.VendaMapper;
import br.com.acaipaideguaweb.service.VendasService;
import br.com.acaipaideguaweb.util.NullUtil;

@RestController
@RequestMapping("/resources/vendas")
public class VendaResources {

	@Autowired
	NotificarPedidoEstabelecimento notificarPedido;

	@Autowired
	NotificarStatusPedidoCliente notificaStatusCliente;

	@Autowired
	private VendasService mVendasService;

	@Autowired
	private ItensVendasRepository mItensVendas;

	Cliente cliente;

	// @Autowired
	// private VendaMapper mVendaMapper;

	private VendaMapper mVendaMapper = Mappers.getMapper(VendaMapper.class);

	private ItemVendaMapper mItemVendaMapper = Mappers.getMapper(ItemVendaMapper.class);

	private ClienteMapper mClienteMapper = Mappers.getMapper(ClienteMapper.class);

	@GetMapping
	public ResponseEntity<List<VendaDTO>> listar() {

		try {

			if (mVendaMapper.vendasTovendaDTOs(mVendasService.findAllByOrderIdDesc()) == null) {
				System.out.println("null");

			} else {
				System.out.println("nao null");

			}

		} catch (NullPointerException e) {
			System.out.println("Erro ao obter" + e.getMessage());
		}

		System.out.println("num" + mVendasService.findAllByOrderIdDesc().size());
		return ResponseEntity.status(HttpStatus.OK)
				.body(mVendaMapper.vendasTovendaDTOs(mVendasService.findAllByOrderIdDesc()));

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Venda venda = mVendasService.obterVenda(id);
		VendaDTO vendaDTO = mVendaMapper.vendaToVendaDTO(venda);

		vendaDTO.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(mItensVendas.findAllByVenda(venda)));
		vendaDTO.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));

		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(vendaDTO);
	}

	@GetMapping(value = { "/estabelecimento/{id}", "/estabelecimento/{id}/data/{data}" })
	public ResponseEntity<List<VendaDTO>> pedidosPorEstabelecimento(@PathVariable("id") Long id,
			@PathVariable(value = "data", required = false) String data)
			throws ParseException, java.text.ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
		VendaDTO vendaDTO;

		if (data == null) {
			List<Venda> vendas = mVendasService.obterVendasPorEstabelecimento(id);
			List<VendaDTO> vendasDTO = new ArrayList<>();

			for (Venda venda : vendas) {
				vendaDTO = mVendaMapper.vendaToVendaDTO(venda);
				vendaDTO.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(mItensVendas.findAllByVenda(venda)));
				vendaDTO.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));
				vendasDTO.add(vendaDTO);
			}

			return ResponseEntity.status(HttpStatus.OK).body(vendasDTO);
		} else {

			// System.out.println("maior
			// data"+mVendasService.maiorDataPedidoPorEstabelecimento(id));
			// System.out.println(mVendasService.maiorDataPedidoPorEstabelecimento(id).after(df.parse(data)));

			if (mVendasService.maiorDataVendaPorEstabelecimento(id).after(df.parse(data))) {
				List<Venda> vendas = mVendasService.obterVendasPorEstabelecimento(id);
				List<VendaDTO> vendasDTO = new ArrayList<>();

				for (Venda venda : vendas) {
					vendaDTO = mVendaMapper.vendaToVendaDTO(venda);
					vendaDTO.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(venda.getItens_vendas()));
					vendaDTO.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));
					vendasDTO.add(vendaDTO);
				}

				return ResponseEntity.status(HttpStatus.OK).body(vendasDTO);

			} else {
				List<VendaDTO> listEmpty = new ArrayList<>();

				return ResponseEntity.status(HttpStatus.OK).body(listEmpty);
			}
		}

	}

	@GetMapping(value = "/cliente/{uid}")
	public ResponseEntity<List<VendaDTO>> pedidosPorCliente(@PathVariable("uid") String uid) throws ParseException {
		List<Venda> vendas = mVendasService.obterVendasPorCliente(uid);
		List<VendaDTO> vendasDTO = new ArrayList<>();
		VendaDTO vendaDTO;

		for (Venda venda : vendas) {
			vendaDTO = mVendaMapper.vendaToVendaDTO(venda);
			vendaDTO.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(mItensVendas.findAllByVenda(venda)));
			vendaDTO.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));
			vendasDTO.add(vendaDTO);
		}

		return ResponseEntity.status(HttpStatus.OK).body(vendasDTO);

	}

	@GetMapping(value = "/estabelecimento/{id}/{data}")
	public ResponseEntity<List<VendaDTO>> pedidosPorEstabelecimentoEData(@PathVariable("id") Long id,
			@PathVariable("data") String data) throws ParseException, java.text.ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yy-MM-dd");
		java.sql.Date sqlDate = new java.sql.Date(df.parse(data).getTime());

		System.out.println(sqlDate);
		List<Venda> vendas = mVendasService.obterVendasPorEstabelecimentoEData(id, sqlDate);
		List<VendaDTO> vendasDTO = new ArrayList<>();
		VendaDTO vendaDTO;

		for (Venda venda : vendas) {
			vendaDTO = mVendaMapper.vendaToVendaDTO(venda);
			vendaDTO.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(mItensVendas.findAllByVenda(venda)));
			vendaDTO.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));
			vendasDTO.add(vendaDTO);
		}

		return ResponseEntity.status(HttpStatus.OK).body(vendasDTO);
	}

	@PostMapping
	public ResponseEntity<VendaDTO> salvar(@Valid @RequestBody VendaDTO vendaDTO,
			@RequestHeader(required = false, value = "refreshedTokenEstabelecimento") String refreshedTokenEstabelecimento)
			throws JSONException, JsonProcessingException, UnsupportedEncodingException {

		Venda venda = mVendasService.salvarFromDTO(vendaDTO);
		VendaDTO vendaDTOSalva = mVendaMapper.vendaToVendaDTO(venda);

		vendaDTOSalva.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(mItensVendas.findAllByVenda(venda)));
		vendaDTOSalva.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));

		System.out.println("token estabelecimento" + refreshedTokenEstabelecimento);

		// Passar as informações dos pedidos
		if (!NullUtil.isNullOrBlank(refreshedTokenEstabelecimento)  ){
			notificarPedido.send(refreshedTokenEstabelecimento, vendaDTOSalva);	
		}
		

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(vendaDTOSalva.getId())
				.toUri();

		return ResponseEntity.status(HttpStatus.OK).body(vendaDTOSalva);
	}

	@PutMapping
	public ResponseEntity<VendaDTO> atualizar(@Valid @RequestBody VendaDTO vendaDTO)
			throws JSONException, JsonProcessingException {

		Venda venda = mVendasService.obterVenda(vendaDTO.getId());

		venda.setStatus(vendaDTO.getStatus());
		venda.setMotivoCancelamento(vendaDTO.getMotivoCancelamento());
		mVendasService.salvar(venda);

		VendaDTO vendaDTOAtualizada = mVendaMapper.vendaToVendaDTO(venda);

		vendaDTOAtualizada
				.setItens_vendas(mItemVendaMapper.item_vendaToItensvendaDTOs(mItensVendas.findAllByVenda(venda)));
		vendaDTOAtualizada.setCliente(mClienteMapper.clienteToClienteDTO(venda.getCliente()));

		System.out.println("size iv" + venda.getItens_vendas());

		// Passar as informações do status do pedido para o cliente
		
		
		
		notificaStatusCliente.send(venda.getCliente().getRefreshedToken(), vendaDTO);
		System.out.println("aqui3");

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(venda.getId()).toUri();

		return ResponseEntity.status(HttpStatus.OK).body(vendaDTO);
	}

}
