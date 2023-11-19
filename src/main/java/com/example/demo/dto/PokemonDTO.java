package com.example.demo.dto;

import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class PokemonDTO {
	private Long id;
	
	@NotBlank
	private String nombre;
	
	@Min(5)
	private Integer ataque;
	
	@Min(5)
	private Integer defensa;
	
	private List<HabilidadUnicaDTO> habilidades;

	// getters and setters
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

	public Integer getAtaque() {
		return ataque;
	}

	public void setAtaque(Integer ataque) {
		this.ataque = ataque;
	}

	public Integer getDefensa() {
		return defensa;
	}

	public void setDefensa(Integer defensa) {
		this.defensa = defensa;
	}

	public List<HabilidadUnicaDTO> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadUnicaDTO> habilidades) {
		this.habilidades = habilidades;
	}
	
	
}
