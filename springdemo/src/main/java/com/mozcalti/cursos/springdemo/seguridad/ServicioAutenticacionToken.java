package com.mozcalti.cursos.springdemo.seguridad;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class ServicioAutenticacionToken {

	private static final String HEADER_NAME = "X-TOKEN";

	private SoporteToken soporteToken;

	public void agregarToken(HttpServletResponse response, Authentication authentication) {

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		response.addHeader(HEADER_NAME, soporteToken.crearToken(userDetails));

	}

	public Authentication validarToken(HttpServletRequest request) {
		String token = request.getHeader(HEADER_NAME);

		if (token != null) {
			UserDetails userDetails = soporteToken.recuperarUsuario(token);

			if (userDetails != null) {
				return new CustomAuthentication(userDetails);
			}

		}

		return null;
	}

	@Autowired
	public void setSoporteToken(SoporteToken soporteToken) {
		this.soporteToken = soporteToken;
	}

}
