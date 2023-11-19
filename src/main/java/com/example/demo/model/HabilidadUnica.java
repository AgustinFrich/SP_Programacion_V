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
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "sys_tiposPkm")
public class HabilidadUnica implements Serializable {
	private static final long serialVersionUID = -4442260431491412972L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tip_id")
	private Long id;
	
	@NotBlank
	@Column(name = "tip_nombre")
	private String nombre;
	
	@NotBlank
	@Column(name = "tip_color")
	private String color;

	@ManyToOne(cascade = {CascadeType.ALL	}, fetch = FetchType.LAZY)
	@JoinColumn(name = "pkmn_id")
	public Pokemon pokemon;

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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Pokemon getPokemon() {
		return pokemon;
	}

	public void setPokemon(Pokemon pokemon) {
		this.pokemon = pokemon;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "TipoPkm [id=" + id + ", nombre=" + nombre + ", color=" + color + ", pokemon=" + pokemon + "]";
	}
	
	
	
}
