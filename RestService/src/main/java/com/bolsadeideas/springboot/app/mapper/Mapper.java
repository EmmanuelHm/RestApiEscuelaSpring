package com.bolsadeideas.springboot.app.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.bolsadeideas.springboot.app.entity.Profesor;
import com.bolsadeideas.springboot.app.model.MProfesor;

@Component("mapper")
public class Mapper {
	
	public static List<MProfesor> convertirProfesor(List<Profesor> profesores){
		
		List<MProfesor> mProfesores = new ArrayList<>();
		
		for(Profesor profesor : profesores ) {
			mProfesores.add( new MProfesor(profesor) );
		}
		
		return mProfesores;
	}

}
