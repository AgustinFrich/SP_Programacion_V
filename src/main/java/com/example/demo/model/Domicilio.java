package com.example.demo.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "sys_domicilio")
public class Domicilio implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1773940526989654494L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dom_id")
	private Long id;
	@Column(name = "dom_calle")
	private String calle;
	@Column(name = "dom_altura")
	private Integer altura;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "per_id")
	public Persona persona;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public Integer getAltura() {
		return altura;
	}

	public void setAltura(Integer altura) {
		this.altura = altura;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public String toString() {
		return "Domicilio [id=" + id + ", calle=" + calle + ", altura=" + altura + ", persona=" + persona + "]";
	}
	
	
	
	
}
