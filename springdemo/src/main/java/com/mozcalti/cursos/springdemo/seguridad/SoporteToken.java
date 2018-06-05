package com.mozcalti.cursos.springdemo.seguridad;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.mozcalti.cursos.springdemo.servicios.ServicioUsuario;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class SoporteToken {

	private static final String LLAVE = "vlX9IngjtjNxNlQ0ZKOPiyTnhu7YK0OomCldPIMhXHCn030BsE";
	private static final int HORAS_VIGENCIA = 8;

	private ServicioUsuario servicioUsuario;

	public String crearToken(UserDetails userDetails) {
		Claims claims = Jwts.claims();

		claims.setSubject(userDetails.getUsername());
		claims.setExpiration(calcularVigencia());

		return Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, LLAVE).compact();

	}

	public UserDetails recuperarUsuario(String token) {
		Claims claims = Jwts.parser().setSigningKey(LLAVE).parseClaimsJws(token).getBody();

		if (esVigente(claims)) {

			String username = claims.getSubject();

			return new CustomUserDetails(servicioUsuario.recuperarUsuario(username));
		} else {
			return null;
		}

	}

	private boolean esVigente(Claims claims) {
		return claims.getExpiration().after(new Date());
	}

	private Date calcularVigencia() {
		Calendar hoy = Calendar.getInstance();

		hoy.add(Calendar.HOUR, HORAS_VIGENCIA);

		return hoy.getTime();
	}

	@Autowired
	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

}
