package com.bolsadeideas.springboot.app.model;

import com.bolsadeideas.springboot.app.entity.Lenguaje;
import com.bolsadeideas.springboot.app.entity.Profesor;

public class ProfesorLenguaje {
	
	private Profesor profesor;
	private Lenguaje lenguaje;
	
	

	public Profesor getProfesor() {
		return profesor;
	}

	public void setProfesor(Profesor profesor) {
		this.profesor = profesor;
	}

	public Lenguaje getLenguaje() {
		return lenguaje;
	}

	public void setLenguaje(Lenguaje lenguaje) {
		this.lenguaje = lenguaje;
	}
	
	

}
