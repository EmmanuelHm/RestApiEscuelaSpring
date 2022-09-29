package com.bolsadeideas.springboot.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bolsadeideas.springboot.app.entity.Profesor;
import com.bolsadeideas.springboot.app.mapper.Mapper;
import com.bolsadeideas.springboot.app.model.MProfesor;
import com.bolsadeideas.springboot.app.service.IProfesorService;

@RestController
@RequestMapping("/api")
public class ProfesorController {
	
	@Autowired
	private IProfesorService profesorService;
	
	/*CONSULTAR TODOS LOS PROFESORES*/
	@GetMapping("/profesores")
	@ResponseStatus(HttpStatus.OK)
	public List<Profesor> getProfesores(){
		return profesorService.findAll();
	}
	
	/*CONSULTAR UN PROFESOR*/
	@PostMapping("/find_profesor")
	public ResponseEntity<?> findProfesor(@RequestBody Profesor profesor){
		Profesor profesorDb = profesorService.findProfesor(profesor);
		
		if(profesorDb != null) {
			return new ResponseEntity<>(profesorDb, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*CREAR UN PROFESOR*/
	@PostMapping("/sign_up")
	public ResponseEntity<Void> addProfesor(@RequestBody Profesor profesor){
	
		if( profesorService.findProfesor(profesor) == null ) {
			profesorService.save(profesor);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}
	
	/*LOGIN PARA PROFESORES*/
	@PostMapping("/login")
	public ResponseEntity<?> loginProfesor(@RequestBody Profesor profesor){
		 Profesor profesorDb = profesorService.checkProfesorLogin(profesor);
		 
		 if(profesorDb != null) {
			 
			 List<Profesor> profesores = new ArrayList<>();
			 profesores.add(profesorDb);
			 
			 List<MProfesor> mProfesores = new ArrayList<>();
			 mProfesores = Mapper.convertirProfesor(profesores);
			 
			 return new ResponseEntity<>(mProfesores, HttpStatus.OK);
		 }
		 else {
			 return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		 }
	}
	
	/*ACTUALIZAR UN PROFESOR*/
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateProfesor(@PathVariable(value = "id") Long id, @RequestBody Profesor profesor){
		
		Profesor profesorDb = null;
		profesorDb = profesorService.findById(id);
		
		if(profesorDb != null) {
			
			//Update
			profesorDb.setNombre(profesor.getNombre());
			profesorDb.setEmail(profesor.getEmail());
			//profesorDb.setPassword(profesor.getPassword());
			
			profesorService.updateProfesor(profesorDb);
			return new ResponseEntity<>(profesorDb, HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*ACTUALIZAR UN PROFESOR 2*/
	@PutMapping("/update_sql")
	public ResponseEntity<?> updateProfesorSql(@RequestBody Profesor profesor){
		
		Profesor profesorDb = null;
		profesorDb = profesorService.findById(profesor.getId());
		
		if(profesorDb != null) {
			
			//Update
			profesorDb.setNombre(profesor.getNombre());
			profesorDb.setEmail(profesor.getEmail());
			//profesorDb.setPassword(profesor.getPassword());
			
			profesorService.updateProfesor(profesorDb);
			return new ResponseEntity<>(profesorDb, HttpStatus.OK);			
		}
		else {
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		}
	}
	
	/*BORRAR UN PROFESOR*/
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProfesor(@PathVariable(value="id") Long id){
		profesorService.deleteProfesor(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/*BORRAR TODOS LOS PROFESOR*/
	@DeleteMapping("/delete")
	public ResponseEntity<Void> deleteAllProfesores(){
		profesorService.deleteAllProfesores();
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/*---------------------------EXTRAS---------------------------*/
	
	/*BORRAR PROFESOR CON METODO POST*/
	@PostMapping("/delete_post")
	public ResponseEntity<Void> deleteProfesorPost(@RequestBody Profesor profesor){
		
		if(profesorService.findProfesor(profesor) != null) {
			profesorService.deleteProfesor(profesor);
			return new ResponseEntity<Void>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}
	

}
