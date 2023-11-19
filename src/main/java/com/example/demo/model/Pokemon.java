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
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "sys_pokemones")
public class Pokemon implements Serializable {
	private static final long serialVersionUID = 5133784777719392748L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pkmn_id")
	private Long id;
	
	@NotBlank
	@Column(name = "pkmn_nombre")
	private String nombre;
	
	@Min(5)
	@Column(name = "pkmn_ataque")
	private Integer ataque;
	
	@Min(5)
	@Column(name = "pkmn_defensa")
	private Integer defensa;
	
	@OneToMany(mappedBy = "pokemon", cascade = {CascadeType.ALL})
	private List<HabilidadUnica> habilidades;

	// Setters y getters
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

	public List<HabilidadUnica> getHabilidades() {
		return habilidades;
	}

	public void setHabilidades(List<HabilidadUnica> habilidades) {
		this.habilidades = habilidades;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", nombre=" + nombre + ", ataque=" + ataque + ", defensa=" + defensa + ", tipos="
				+ habilidades + "]";
	}

	
	
}
