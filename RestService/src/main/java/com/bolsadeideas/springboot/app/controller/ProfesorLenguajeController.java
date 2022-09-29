package com.bolsadeideas.springboot.app.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.entity.Lenguaje;
import com.bolsadeideas.springboot.app.entity.Profesor;
import com.bolsadeideas.springboot.app.model.ProfesorLenguaje;
import com.bolsadeideas.springboot.app.service.ILenguajeService;
import com.bolsadeideas.springboot.app.service.IProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorLenguajeController {
	
	@Autowired
	private ILenguajeService lenguajeService;
	
	@Autowired
	private IProfesorService profesorService;
	
	
	@PostMapping("/lenguajes_profesor")
	public ResponseEntity<?> listaLenguajesProfesor(@RequestBody Profesor profesor){
		
		Profesor profesorDb = profesorService.findById(profesor.getId());
		
		if(profesorDb != null) {
			Collection<Lenguaje> listaLenguajes = profesorDb.getLenguajes();
			
			if(listaLenguajes != null) {
				return new ResponseEntity<>(listaLenguajes, HttpStatus.OK);
			}
		}
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	
	@PostMapping("/save_lenguaje_profesor")
	public ResponseEntity<?> saveLenguajeProfesor(@RequestBody ProfesorLenguaje profesorLenguaje){
		
		Profesor profesorDb= profesorService.findById(profesorLenguaje.getProfesor().getId());
		
		if(profesorDb != null) {
			Lenguaje lenguajeDb = lenguajeService.findLenguajeById(profesorLenguaje.getLenguaje().getId());
			profesorDb.addLenguaje(lenguajeDb);
			profesorService.save(profesorDb);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}

}
