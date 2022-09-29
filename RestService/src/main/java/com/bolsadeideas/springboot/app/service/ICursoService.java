package com.bolsadeideas.springboot.app.service;

import java.util.List;

import com.bolsadeideas.springboot.app.entity.Curso;

public interface ICursoService {
	
	public List<Curso> findAll();
	
	public void saveCurso(Curso curso);
	
	public List<Curso> getCursosProfesor(Long id);

}
