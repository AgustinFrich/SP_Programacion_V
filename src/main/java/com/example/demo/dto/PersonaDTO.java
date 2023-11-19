package com.example.demo.dto;

import java.util.List;

public class PersonaDTO {
	private Long id;
	private String nombre;
	private String apellido;
	private Integer dni;
	private List<DomicilioDTO> domicilios;
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
	public List<DomicilioDTO> getDomicilios() {
		return domicilios;
	}
	public void setDomicilios(List<DomicilioDTO> domicilios) {
		this.domicilios = domicilios;
	}
	
}
