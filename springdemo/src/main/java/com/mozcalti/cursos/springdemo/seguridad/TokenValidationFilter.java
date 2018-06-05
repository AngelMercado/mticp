package com.mozcalti.cursos.springdemo.seguridad;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

public class TokenValidationFilter extends GenericFilterBean {

	private ServicioAutenticacionToken servicioAutenticacionToken;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;

		Authentication authentication = servicioAutenticacionToken.validarToken(httpRequest);

		SecurityContextHolder.getContext().setAuthentication(authentication);

		chain.doFilter(request, response);

	}

	@Autowired
	public void setServicioAutenticacionToken(ServicioAutenticacionToken servicioAutenticacionToken) {
		this.servicioAutenticacionToken = servicioAutenticacionToken;
	}
	
	

}
