package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.model.Pokemon;
import com.example.demo.model.HabilidadUnica;


@Component
public class PokemonMapper {
	
	@Autowired HabilidadUnicaMapper habilidadUnicaMapper;
	
	public Pokemon dtoToEntity(PokemonDTO dto) {
		Pokemon entity = new Pokemon();
		entity.setId(dto.getId());
		entity.setNombre(dto.getNombre());
		entity.setAtaque(dto.getAtaque());
		entity.setDefensa(dto.getDefensa());
		entity.setHabilidades(new ArrayList<HabilidadUnica>());

		if(dto.getHabilidades() != null) {	
			for(HabilidadUnicaDTO habilidadDTO : dto.getHabilidades()) {
				HabilidadUnica habilidad = habilidadUnicaMapper.dtoToEntity(habilidadDTO);
				entity.getHabilidades().add(habilidad);
			}
		}
		return entity;
	}
	
	public PokemonDTO entityToDTO(Pokemon pkmn) {
		PokemonDTO dto = new PokemonDTO();
		dto.setId(pkmn.getId());
		dto.setNombre(pkmn.getNombre());
		dto.setAtaque(pkmn.getAtaque());
		dto.setDefensa(pkmn.getDefensa());
			dto.setHabilidades(new ArrayList<HabilidadUnicaDTO>());
	
		if(pkmn.getHabilidades() != null) {			
			for(HabilidadUnica habilidad : pkmn.getHabilidades()) {
				HabilidadUnicaDTO habilidadDTO = habilidadUnicaMapper.entityToDTO(habilidad);
				dto.getHabilidades().add(habilidadDTO);
			}
		}
		
		return dto;
	}
	
	
	public List<PokemonDTO> entityToDTO(List<Pokemon> pkmns) {
		List<PokemonDTO> dto = new ArrayList<PokemonDTO>();
		for(Pokemon p : pkmns) {
			dto.add(this.entityToDTO(p));
		}
		return dto;
	}
	
	/*
	 * Si trato de agregar el pokemon a la habilidad sin sacar sus habilidades se vuelve recursivo y me explota todo
	 * */
	public Pokemon dtoToEntitySinRecursividad(PokemonDTO dto) {
		Pokemon p = dtoToEntity(dto);
		
		List<HabilidadUnica> habilidades = p.getHabilidades();
		int size = habilidades.size();
		Pokemon tmp = new Pokemon();
		tmp.setNombre(p.getNombre());
		tmp.setAtaque(p.getAtaque());
		tmp.setDefensa(p.getDefensa());
		tmp.setId(p.getId());
		
		
		for (int i = 0; i < size; i++) {
			habilidades.get(i).pokemon = tmp;
		}
		
		p.setHabilidades(habilidades);
		return p;
	}
}
