package com.mozcalti.cursos.springdemo.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.mozcalti.cursos.springdemo.entidades.Usuario;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {

	Usuario findByUsername(String username);

}
