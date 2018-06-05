package com.mozcalti.cursos.springdemo.seguridad;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.mozcalti.cursos.springdemo.entidades.Usuario;

public class CustomUserDetails implements UserDetails {

	private Usuario usuario;

	public CustomUserDetails(Usuario usuario) {
		this.usuario = usuario;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {

		Set<GrantedAuthority> authorities = new HashSet<>();

		authorities.add(new CustomGrantedAuthority(usuario.getRol()));

		return authorities;
	}

	public String getPassword() {
		return usuario.getPassword();
	}

	public String getUsername() {
		return usuario.getUsername();
	}

	public boolean isAccountNonExpired() {

		return true;
	}

	public boolean isAccountNonLocked() {

		return true;
	}

	public boolean isCredentialsNonExpired() {

		return true;
	}

	public boolean isEnabled() {
		return true;
	}

}
