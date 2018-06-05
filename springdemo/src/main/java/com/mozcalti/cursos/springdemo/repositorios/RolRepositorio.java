package com.mozcalti.cursos.springdemo.repositorios;

import org.springframework.data.repository.CrudRepository;

import com.mozcalti.cursos.springdemo.entidades.Rol;

public interface RolRepositorio extends CrudRepository<Rol, Long> {

	Rol findByNombre(String nombre);
	
}
