package com.bolsadeideas.springboot.app.service;

import java.util.List;
//import java.util.Optional;

import com.bolsadeideas.springboot.app.entity.Lenguaje;

public interface ILenguajeService {
	
	public List<Lenguaje> findAll();
	
	public void saveLenguaje(Lenguaje lenguaje);
	
	//public Optional<Lenguaje> findById(Long id);
	
	public Lenguaje findLenguajeById(Long id);
	
}
