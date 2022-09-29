package com.bolsadeideas.springboot.app.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "lenguajes")
public class Lenguaje implements Serializable {

	/*----------------------ATTRIBUTES----------------------*/
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
		@Column
		private String nombre;
	
		@Column(name = "date")
		@Temporal(TemporalType.DATE)
		private Date date;
	
		/*-------------RELACION MUCHOS A MUCHOS CON 'PROFESORES'-------------*/
		@ManyToMany
		@JoinTable(name = "profesores_lenguajes", joinColumns = @JoinColumn(name = "lenguaje_id", referencedColumnName = "id"), 
		inverseJoinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "id"))
		private Set<Profesor> profesores = new HashSet<Profesor>();
		
	/*-----------------Extra Methods-----------------*/
		//Metodo para settear la fecha automaticamente
		@PrePersist
		public void prePersist() {
			date = new Date();
		}

	/*----------------------GETTERS & SETTERS----------------------*/
		public Long getId() {
			return id;
		}
	
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getNombre() {
			return nombre;
		}
	
		public void setNombre(String nombre) {
			this.nombre = nombre;
		}
	
		public Date getDate() {
			return date;
		}
	
		public void setDate(Date date) {
			this.date = date;
		}
	
		public Set<Profesor> getProfesores() {
			return profesores;
		}
	
		public void setProfesores(Set<Profesor> profesores) {
			this.profesores = profesores;
		}
		
		
		private static final long serialVersionUID = 1L;
}
