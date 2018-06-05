package com.mozcalti.cursos.springdemo.seguridad;
import org.springframework.security.core.GrantedAuthority;

import com.mozcalti.cursos.springdemo.entidades.Rol;

public class CustomGrantedAuthority implements GrantedAuthority {

	private Rol rol;

	public CustomGrantedAuthority(Rol rol) {
		this.rol = rol;
	}

	public String getAuthority() {
		return "ROLE_" + rol.getNombre();
	}

}
