package com.bolsadeideas.springboot.app.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "profesores")
public class Profesor implements Serializable {
	
	/*-------------------Attributes-------------------*/
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Long id;
	
		@Column(name = "nombre")
		private String nombre;
	
		@Column(length = 60, unique = true)
		private String email;
	
		private String password;
	
		@Column(length = 2000)
		private String foto;
	
		@Column(name = "create_at")
		@Temporal(TemporalType.DATE)
		private Date createAt;
		
	/*--------------RELACION 1 A MUCHOS CON 'CURSOS'--------------*/
		@OneToMany(cascade = CascadeType.ALL)
		@JoinColumn(name = "profesor_id", referencedColumnName = "id")
		private List<Curso> curso = new ArrayList<>();
		
	/*--------------RELACION MUCHOS A MUCHOS CON 'LENGUAJES'--------------*/
		@ManyToMany(cascade = CascadeType.ALL)
		@JsonBackReference
		@JoinTable(name = "profesores_lenguajes", joinColumns = @JoinColumn(name = "profesor_id", referencedColumnName = "id"),
				   inverseJoinColumns = @JoinColumn(name = "lenguaje_id", referencedColumnName = "id") )
		private Set<Lenguaje> lenguajes = new HashSet<Lenguaje>();
	
	
	/*-----------------Extra Methods-----------------*/
		//Metodo para settear la fecha automaticamente
		@PrePersist
		public void prePersist() {
			createAt = new Date();
		}

	/*---------------Getters & Setters---------------*/
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
	
		public String getEmail() {
			return email;
		}
	
		public void setEmail(String email) {
			this.email = email;
		}
	
		public String getPassword() {
			return password;
		}
	
		public void setPassword(String password) {
			this.password = password;
		}
	
		public String getFoto() {
			return foto;
		}
	
		public void setFoto(String foto) {
			this.foto = foto;
		}
	
		public Date getCreateAt() {
			return createAt;
		}
	
		public void setCreateAt(Date createAt) {
			this.createAt = createAt;
		}

		public List<Curso> getCurso() {
			return curso;
		}

		public void setCurso(List<Curso> curso) {
			this.curso = curso;
		}
		
		public Set<Lenguaje> getLenguajes() {
			return lenguajes;
		}

		public void setLenguajes(Set<Lenguaje> lenguajes) {
			this.lenguajes = lenguajes;
		}
		
		public void addLenguaje(Lenguaje lenguaje) {
			this.lenguajes.add(lenguaje);
		}


	private static final long serialVersionUID = 1L;
}
