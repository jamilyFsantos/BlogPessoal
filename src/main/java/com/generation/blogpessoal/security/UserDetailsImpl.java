package com.generation.blogpessoal.security;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.generation.blogpessoal.model.Usuario;

public class UserDetailsImpl implements UserDetails{
 
	private static final long serialVersionUID = 1L;

	private String userName;
	private String password;
	
	//autoriza todos os privilegios de usuario
	private List<GrantedAuthority> authorities;

	public UserDetailsImpl(Usuario usuario) {
		this.userName = usuario.getUsuario();
		this.password = usuario.getSenha();
	}

	
	//Metodos padrões
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}
	
	@Override
	public String getUsername() {

		return userName;
	}

	//perguntas do security 
	//se a conta não está experirada
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	//se a conta está bloqueada
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	//se a credencial não está expirada
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	//se a conta está ativa
	@Override
	public boolean isEnabled() {
		return true;
	}

}
