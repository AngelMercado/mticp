package com.mozcalti.cursos.springdemo.controladores;

import java.util.Date;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prueba")
public class ControladorPrueba {

	@RequestMapping(value = "/ping", method = RequestMethod.GET)
	public String metodoPrueba() {
		return (new Date()).toString();
	}
	
	@RequestMapping(value="/concatenar/{parametroUno}/{parametroDos}", method=RequestMethod.GET)
	public String concatenar(@PathVariable String parametroUno,@PathVariable String parametroDos)
	{
		return parametroUno + " " + parametroDos;
	}
	

}
