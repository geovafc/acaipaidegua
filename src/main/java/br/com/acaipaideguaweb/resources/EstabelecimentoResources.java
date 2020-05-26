package br.com.acaipaideguaweb.resources;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.monitorjbl.json.JsonResult;
import com.monitorjbl.json.JsonView;
import com.monitorjbl.json.Match;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.repositorio.AtualizacaoRepository;
import br.com.acaipaideguaweb.resources.dto.EstabelecimentoDTO;
import br.com.acaipaideguaweb.resources.dto.mapper.EstabelecimentoMapper;
import br.com.acaipaideguaweb.resources.dto.mapper.HorarioMapper;
import br.com.acaipaideguaweb.resources.dto.mapper.TabelaPrecoMapper;
import br.com.acaipaideguaweb.resources.dto.mapper.TelefoneMapper;
import br.com.acaipaideguaweb.service.EstabelecimentoService;

@RestController
@RequestMapping("/resources/estabelecimento")
public class EstabelecimentoResources {

	private JsonResult json = JsonResult.instance();
	@Autowired
	private EstabelecimentoService estabelecimentoService;

	@Autowired
	AtualizacaoRepository atualizacaoRepository;

	private EstabelecimentoMapper mEstabelecimentoMapper = Mappers.getMapper(EstabelecimentoMapper.class);
	private HorarioMapper mHorarioMapper = Mappers.getMapper(HorarioMapper.class);
	private TelefoneMapper mTelefoneMapper = Mappers.getMapper(TelefoneMapper.class);
	private TabelaPrecoMapper mTabelaPrecoMapper = Mappers.getMapper(TabelaPrecoMapper.class);

	@GetMapping
	public ResponseEntity<List<Estabelecimento>> listarTodos() {
		return ResponseEntity.status(HttpStatus.OK).body(estabelecimentoService.listarTodos());
	}

	@GetMapping(value = { "todos", "todos/{data}" })
	public ResponseEntity<List<Estabelecimento>> listarComSelos(
			@PathVariable(value = "data", required = false) String data)
			throws JsonProcessingException, ParseException, java.text.ParseException {

		SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

		if (data == null) {
			List<Estabelecimento> list = estabelecimentoService.comSelos(true);
			return ResponseEntity.status(HttpStatus.OK)
					.body(json
							.use(JsonView.with(list).onClass(Estabelecimento.class,
									Match.match().exclude("email").exclude("refreshedToken").exclude("responsavel")
											.exclude("dataCadastro").exclude("cep").exclude("cidade").exclude("uf")
											.exclude("comSelo").exclude("observacao").exclude("taxa")
											.exclude("telefones").exclude("listaPrecos").exclude("Horarios")))
							.returnValue());
		} else if (atualizacaoRepository.getOne(new Long(1)).getDataAtualizacao().after(df.parse(data))) {
			System.out.println("Atualiza");

			List<Estabelecimento> list = estabelecimentoService.comSelos(true);
			return ResponseEntity.status(HttpStatus.OK)
					.body(json
							.use(JsonView.with(list).onClass(Estabelecimento.class,
									Match.match().exclude("email").exclude("refreshedToken").exclude("responsavel")
											.exclude("dataCadastro").exclude("cep").exclude("cidade").exclude("uf")
											.exclude("comSelo").exclude("observacao").exclude("taxa")
											.exclude("telefones").exclude("listaPrecos").exclude("Horarios")))
							.returnValue());

		} else {
			List<Estabelecimento> list = new ArrayList<>();
			return ResponseEntity.status(HttpStatus.OK).body(list);
		}

	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> buscar(@PathVariable("id") Long id) {
		Estabelecimento estabelecimento = estabelecimentoService.buscar(id);
		EstabelecimentoDTO mEstabelecimentoDTO = mEstabelecimentoMapper
				.estabelecimentoToEstabelecimentoDTO(estabelecimento);
		mEstabelecimentoDTO.setHorarios(mHorarioMapper.horariosToHorarioDTOs(estabelecimento.getHorarios()));
		mEstabelecimentoDTO
				.setListaPrecos(mTabelaPrecoMapper.tabelaPrecosToTabelaPrecoDTOs(estabelecimento.getListaPrecos()));
		mEstabelecimentoDTO.setTelefones(mTelefoneMapper.telefonesToTelefoneDTOs(estabelecimento.getTelefones()));

		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(mEstabelecimentoDTO);
	}

}
