package com.mozcalti.cursos.springdemo.herramientas;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class SoportePassword {

	public String cifrar(String password) {
		String salt = BCrypt.gensalt();

		return BCrypt.hashpw(password, salt);
	}

}
