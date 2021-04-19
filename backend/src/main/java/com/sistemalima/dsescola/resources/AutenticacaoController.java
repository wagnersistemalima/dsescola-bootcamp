package com.sistemalima.dsescola.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistemalima.dsescola.config.security.TokenService;
import com.sistemalima.dsescola.dto.LoginDTOForm;
import com.sistemalima.dsescola.dto.TokenDTO;

// Controller de autenticação


@RestController
@RequestMapping(value = "/auth")
public class AutenticacaoController {
	
	// injeção de dependencia para a classe AuthenticationManager
	
	@Autowired
	private AuthenticationManager authManager;
	
	// injeçao de dependencia para a classe TokenService no pacote de segurança
	
	@Autowired
	private TokenService tokenService;
	
	// end point que recebe dados via autenticação / metodo post
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar (@RequestBody LoginDTOForm form) {
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		System.out.println(dadosLogin);
		try {
			Authentication authenticatiom = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticatiom);
			
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		}
		catch (AuthenticationCredentialsNotFoundException e) {
			return ResponseEntity.badRequest().build();
		}
	}

}
