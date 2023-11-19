package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "sys_persona")
public class Persona implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4373752536372145558L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "per_id")
	private Long id;
	@Column(name = "per_nombre")
	private String nombre;
	@Column(name = "per_apellido")
	private String apellido;
	
	@Column(name = "per_dni")
	private Integer dni;

	@OneToMany(mappedBy = "persona", cascade = CascadeType.ALL)
	private List<Domicilio> domicilios;
	
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Integer getDni() {
		return dni;
	}
	public void setDni(Integer dni) {
		this.dni = dni;
	}
	
	
	
	public List<Domicilio> getDomicilios() {
		return domicilios;
	}
	public void setDomicilios(List<Domicilio> domicilios) {
		this.domicilios = domicilios;
	}
	@Override
	public String toString() {
		return "Persona [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", dni=" + dni + "]";
	}
		
	
}

