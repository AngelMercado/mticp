package com.mozcalti.cursos.springdemo.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.mozcalti.cursos.springdemo.entidades.Rol;
import com.mozcalti.cursos.springdemo.entidades.Usuario;
import com.mozcalti.cursos.springdemo.herramientas.SoportePassword;
import com.mozcalti.cursos.springdemo.repositorios.RolRepositorio;
import com.mozcalti.cursos.springdemo.repositorios.UsuarioRepositorio;

@Service
public class ServicioUsuario {

	private SoportePassword soportePassword;
	private UsuarioRepositorio usuarioRepositorio;
	private RolRepositorio rolRepositorio;

	@Secured("ROLE_ADMINISTRADOR")
	public Usuario crearUsuario(String username, String password) {
		Usuario usuario = new Usuario();
		usuario.setUsername(username);
		usuario.setPassword(soportePassword.cifrar(password));

		return usuarioRepositorio.save(usuario);
	}

	@Secured({"ROLE_ADMINISTRADOR","ROLE_USUARIO"})
	public Usuario recuperarUsuario(Long idUsuario) {
		return usuarioRepositorio.findOne(idUsuario);
	}
	
	public Usuario recuperarUsuario(String username) {
		return usuarioRepositorio.findByUsername(username);
	}

	@Secured("ROLE_ADMINISTRADOR")	
	public Usuario asignarRol(Long idUsuario, Long idRol) {
		Usuario usuario = usuarioRepositorio.findOne(idUsuario);
		Rol rol = rolRepositorio.findOne(idRol);

		usuario.setRol(rol);

		return usuarioRepositorio.save(usuario);

	}

	@Secured("ROLE_ADMINISTRADOR")
	public Usuario cambiarPassword(Long idUsuario, String password) {
		Usuario usuario = usuarioRepositorio.findOne(idUsuario);

		usuario.setPassword(soportePassword.cifrar(password));

		return usuarioRepositorio.save(usuario);
	}

	@Secured("ROLE_ADMINISTRADOR")
	public String eliminarUsuario(Long idUsuario) {
		usuarioRepositorio.delete(idUsuario);

		return "Usuario eliminado";
	}

	@Autowired
	public void setSoportePassword(SoportePassword soportePassword) {
		this.soportePassword = soportePassword;
	}

	@Autowired
	public void setUsuarioRepositorio(UsuarioRepositorio usuarioRepositorio) {
		this.usuarioRepositorio = usuarioRepositorio;
	}

	@Autowired
	public void setRolRepositorio(RolRepositorio rolRepositorio) {
		this.rolRepositorio = rolRepositorio;
	}

}
