package com.mozcalti.cursos.springdemo.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mozcalti.cursos.springdemo.entidades.Usuario;
import com.mozcalti.cursos.springdemo.servicios.ServicioUsuario;

@RestController
@RequestMapping("/usuario")
public class UsuarioControlador {

	private ServicioUsuario servicioUsuario;

	@RequestMapping(value = "", method = RequestMethod.POST)
	public Usuario nuevoUsuario(String username, String password) {
		return servicioUsuario.crearUsuario(username, password);
	}

	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.GET)
	public Usuario obtenerUsuario(@PathVariable Long idUsuario) {
		return servicioUsuario.recuperarUsuario(idUsuario);
	}

	@RequestMapping(value = "/{idUsuario}/password", method = RequestMethod.PUT)
	public Usuario cambiarPassword(@PathVariable Long idUsuario, @RequestParam String password) {
		return servicioUsuario.cambiarPassword(idUsuario, password);
	}

	@RequestMapping(value = "/{idUsuario}/rol", method = RequestMethod.PUT)
	public Usuario cambiarRol(@PathVariable Long idUsuario, @RequestParam Long idRol) {
		return servicioUsuario.asignarRol(idUsuario, idRol);
	}

	@RequestMapping(value = "/{idUsuario}", method = RequestMethod.DELETE)
	public String eliminarUsuario(@PathVariable Long idUsuario) {
		return servicioUsuario.eliminarUsuario(idUsuario);
	}

	@Autowired
	public void setServicioUsuario(ServicioUsuario servicioUsuario) {
		this.servicioUsuario = servicioUsuario;
	}

}
