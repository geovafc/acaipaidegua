package br.com.acaipaideguaweb.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.acaipaideguaweb.model.Estabelecimento;
import br.com.acaipaideguaweb.model.Usuario;
import br.com.acaipaideguaweb.resources.dto.UsuarioDTO;
import br.com.acaipaideguaweb.service.EstabelecimentoService;
import br.com.acaipaideguaweb.service.UsuarioService;


@RestController
@RequestMapping("/resources/usuario")
public class UsuarioResource {
	
	@Autowired
	private UsuarioService mUsuarioService;
	@Autowired
	private EstabelecimentoService mEstabelecimentoService;

	private Map <String,String > estabelecimentos;
	private List<Map> maps;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> logar(@RequestHeader("refreshedTokenEstabelecimento") String idDevice,
			@RequestHeader("email") String email,@RequestHeader("password") String password) throws Exception {

		System.out.println("Em"+email+"Senha: "+password);

		BCryptPasswordEncoder mBCryptPasswordEncoder = new BCryptPasswordEncoder();
		
		Usuario usuario = mUsuarioService.findByEmail(email);	
		
		if ( usuario  != null){
			
			if (mBCryptPasswordEncoder.matches(password, usuario.getPassword())){
				Estabelecimento e = usuario.getListaEstabelecimentos().get(0);
				System.out.println("true");

				
		if (e.getRefreshedToken() == null || !e.getRefreshedToken().equals(idDevice) ){			
			e.setRefreshedToken(idDevice);			
			mEstabelecimentoService.salvar(e);
		}
		
		
		UsuarioDTO mUsuarioDTO = new UsuarioDTO();
		setarUsuarioDTO(mUsuarioDTO, usuario);
		
		
		CacheControl cacheControl = CacheControl.maxAge(20, TimeUnit.SECONDS);

		return ResponseEntity.status(HttpStatus.OK).cacheControl(cacheControl).body(mUsuarioDTO);
			}else {
				return ResponseEntity.status(HttpStatus.OK).body("Senha incorreta! ");

				
			}
			
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("Email não encontrado");

		}
		
		


	}
	
	private void setarUsuarioDTO(UsuarioDTO mUsuarioDTO, Usuario usuario ){
		estabelecimentos = new HashMap<>();
		maps = new ArrayList<>();
		mUsuarioDTO.setId(usuario.getId());
		mUsuarioDTO.setUsername(usuario.getUsername());
		
		
	//	for (Estabelecimento e :  usuario.getListaEstabelecimentos()){
		Estabelecimento e = usuario.getListaEstabelecimentos().get(0);
		if (e != null){
			
		
			estabelecimentos.put("id", e.getId().toString());
			estabelecimentos.put("nome", e.getNome());
			
			maps.add(estabelecimentos);
		}
		
		//}
		mUsuarioDTO.setEstabelecimentos(maps);
	}

}
