package br.com.acaipaideguaweb.controller;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.ItemVenda;
import br.com.acaipaideguaweb.model.StatusEstabelecimento;
import br.com.acaipaideguaweb.model.StatusVenda;
import br.com.acaipaideguaweb.model.Venda;
import br.com.acaipaideguaweb.repositorio.filter.VendaFilter;
import br.com.acaipaideguaweb.service.VendasService;

@Controller
@RequestMapping("/vendas")
public class VendaController {

	private static final String VENDAS_VIEW = "venda/vendas";

	@Autowired
	private VendasService vendasService;

	@GetMapping
	public ModelAndView listarVendas() {
		ModelAndView view = new ModelAndView(VENDAS_VIEW);
		List<Venda> vendas = vendasService.findAllByOrderIdDesc();
		view.addObject("vendas", vendas);
		return view;

	}

	@GetMapping("/detalhe/{id}")
	public ModelAndView detalhes(@PathVariable("id") Venda venda) {

		ModelAndView view = new ModelAndView("venda/DetalhesVenda");

		view.addObject(venda);

		return view;

	}

	@GetMapping("/add")
	public ModelAndView addVendas() {
		ModelAndView view = new ModelAndView("venda/cadastrarVenda");
		view.addObject("venda", new Venda());
		view.addObject("estabelecimentos", vendasService.listarEstabelecimento());
		view.addObject("clientes", vendasService.listarClientes());

		return view;

	}

	@ModelAttribute("formasPagamento")
	public List<String> formasPagamentos() {
		String pagamentos[] = { "DINHEIRO", "CHEQUE", "CARTÃO" };
		return Arrays.asList(pagamentos);
	}

	@GetMapping("/pesquisarEstabelecimento")
	public ModelAndView pesquisarEstabelecimento(@ModelAttribute("filtro") VendaFilter filtro) {
		ModelAndView view = new ModelAndView("venda/adicionarEstabelecimentoClienteVenda");

		Venda venda = new Venda();
		venda.setEstabelecimento(vendasService.filtrarEstabelecimento(filtro));
		venda.setCliente(vendasService.filtrarCliente(filtro));
		view.addObject("venda", venda);

		return view;
	}

	@PostMapping
	public ModelAndView salvar(Venda venda) {
		ModelAndView view = new ModelAndView("venda/vendas");

		venda = vendasService.salvar(venda);

		// ItemVenda item = new ItemVenda();
		//
		// item.setVenda(venda);
		//
		// view.addObject("item", item);

		return view;
	}

	// @GetMapping("/detalhe/{id}")
	public String visualizarItens(@PathVariable Long id, ModelMap model) {

		model.addAttribute("itens_vendas", vendasService.obterVenda(id).getItens_vendas());
		model.addAttribute("venda", vendasService.obterVenda(id));
		return "venda/vendas :: modalContents";

	}
}
