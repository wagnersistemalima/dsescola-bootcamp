package com.sistemalima.dsescola.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sistemalima.dsescola.config.security.AutenticacaoService;
import com.sistemalima.dsescola.config.security.AutenticacaoViaTokenFilter;
import com.sistemalima.dsescola.config.security.TokenService;
import com.sistemalima.dsescola.repositories.UserRepository;

// Classe de configuração de segurança

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	// metodo para fazer injeção de dependencia para AuthenticationManager
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	// injeção de dependencia para a classe de autenticação
	
	@Autowired
	private AutenticacaoService autenticacaoService;
	
	// 1º metodo de configuração de autenticação auth, parte de controle de acesso
	// Login
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(autenticacaoService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	// 2º metodo configuraçoes de Autorização /  parte de url , quem pode acessar aquela url
	// perfil de acesso
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/alunos").permitAll()
		.antMatchers(HttpMethod.GET, "/alunos/*").permitAll()
		.antMatchers(HttpMethod.GET, "/formularios").permitAll()
		.antMatchers(HttpMethod.GET, "/avaliacoes").permitAll()
		.antMatchers(HttpMethod.GET, "/avaliacoes/*").permitAll()
		.antMatchers(HttpMethod.POST, "/auth").permitAll()              // para se autenticar
		.antMatchers(HttpMethod.GET, "/actuator/**").permitAll()    // em produção retirar esta linha
		.antMatchers(HttpMethod.DELETE, "/alunos/*").hasRole("MODERADOR")   // perfil moderador pode deletar aluno
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new AutenticacaoViaTokenFilter(tokenService, userRepository), UsernamePasswordAuthenticationFilter.class);
	}
	
	// 3º metodo para configuração de recursos estaticos (requisiçoes para arquivos)
	// javascript, css, imagens
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring()
        .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
	}
	
	// metodo main para criar uma senha criptografada hasch
	
	//public static void main(String[] args) {
	//	System.out.println(new BCryptPasswordEncoder().encode("123456"));
	//}

}
