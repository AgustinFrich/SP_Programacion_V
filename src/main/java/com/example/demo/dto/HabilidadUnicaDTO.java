package com.example.demo.dto;

import com.example.demo.model.Pokemon;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class HabilidadUnicaDTO {
	private Long id;
	
	@NotBlank
	private String nombre;
	
	@NotBlank
	private String color;

	@NotNull
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
}
