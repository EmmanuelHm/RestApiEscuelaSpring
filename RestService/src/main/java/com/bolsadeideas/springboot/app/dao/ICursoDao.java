package com.bolsadeideas.springboot.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.app.entity.Curso;

public interface ICursoDao extends CrudRepository<Curso, Long>{
	
	public List<Curso> findByProfesorId(Long id);

}
