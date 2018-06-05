package com.mozcalti.cursos.springdemo.seguridad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.mozcalti.cursos.springdemo.servicios.ServicioUsuario;

public class CustomUserDetailsService implements UserDetailsService {

	private ServicioUsuario servicioUsuario;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new CustomUserDetails(servicioUsuario.recuperarUsuario(username));
	}

	@Autowired
	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

}
