package com.mozcalti.cursos.springdemo.seguridad;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	private ServicioAutenticacionToken servicioAutenticacionToken;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		servicioAutenticacionToken.agregarToken(response, authentication);

		response.setStatus(HttpServletResponse.SC_OK);

	}

	@Autowired	
	public void setServicioAutenticacionToken(ServicioAutenticacionToken servicioAutenticacionToken) {
		this.servicioAutenticacionToken = servicioAutenticacionToken;
	}

}
