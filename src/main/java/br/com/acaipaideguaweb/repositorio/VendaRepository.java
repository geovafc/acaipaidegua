package br.com.acaipaideguaweb.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import br.com.acaipaideguaweb.model.Venda;

public interface VendaRepository extends JpaRepository<Venda, Long> {
	
	@Query("SELECT v FROM Venda v WHERE v.estabelecimento.id =:idEstabelecimento  and CAST (v.dataVenda as date)  =:dataVenda")
	public List<Venda> obterVendasPorEstabelecimentoEDataVenda(@Param("idEstabelecimento") Long id, @Param("dataVenda") Date dataVenda);
	
	@Query("SELECT max(v.dataVenda) FROM Venda v WHERE v.estabelecimento.id =:idEstabelecimento")
	public Date maiorDataVendaPorEstabelecimento(@Param("idEstabelecimento")Long id);
	
	//@Query("SELECT v FROM Venda v WHERE v.estabelecimento.id =:idEstabelecimento")
	//public List<Venda> obterVendasPorEstabelecimentoEDataVenda(@Param("idEstabelecimento") Long id, @Param("dataVenda") Date dataVenda);
	
	//@Query("SELECT max(v.dataVenda) FROM Venda v WHERE v.estabelecimento =:idEstabelecimento")
	//public Date maiorDataVendaPorEstabelecimento(@Param("idEstabelecimento")Long id);
	
	@Query("SELECT v FROM Venda v WHERE v.estabelecimento.id =:idEstabelecimento")
	public List<Venda> obterVendasPorEstabelecimento(@Param("idEstabelecimento")Long idEstabelecimento);
	
	//public List<Venda> findByCliente(Cliente cliente);
	
	@Query("SELECT v FROM Venda v WHERE v.cliente.uid =:uid ")

	public List<Venda> obterVendasPorCliente(@Param("uid") String uidCliente);
	
	public List<Venda> findByOrderByIdDesc();
	
	
	
	
	
	
	
	

}
