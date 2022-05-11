package com.generation.blogpessoal.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class BasicSecurityConfig extends WebSecurityConfigurerAdapter {
 
	//serve para comparra os dados digitados com os dados salvos no BD
	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService);
		auth.inMemoryAuthentication()
		.withUser("root")
		.password(passwordEncoder().encode("root"))
		.authorities("ROLE_USER");
	}
	
	//notação que deixa uma função acessivel globalmente(em toda a minha aplicação)
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}


	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.authorizeRequests()
		
		//De qualquer lugar, você terá acesso a login
		.antMatchers("/usuarios/logar").permitAll()
		
		// e cadastro já que as rotas estão abertas
		.antMatchers("/usuarios/cadastrar").permitAll()
		
		// Permite que as rotas estejam acessíveis com GET;  Permite saber quais métodos estão abertos na documentação da API e que estão abertos nela 
        // e é possível utilizar eles.
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		
		//Para outras requisições, tem que está ou cadastrado ou em memória
		.anyRequest().authenticated()
		
		//Define que só será aceito métodos CRUD
		.and().httpBasic()
		
		// Define que toda requisição tem começo, meio e fim. Uma por vez e ajuda a prevenir ataques 
		//cibernéticos e invasões com várias requisições de uma forma | Tipo quando expira o token 
		//em um site como na plataforma da Generation Brasil
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		
		//* Funciona como o '@CrossOrigins', vendo de qual porta está vindo a requisição e
		//liberando acesso para todas (Do Front-end pro Back-end basicamente) */
		.and().cors()
		
		//Autoriza PUT e DELETE na requisição
		.and().csrf().disable();

	}

}
