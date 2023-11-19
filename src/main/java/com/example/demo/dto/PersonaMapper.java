package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.Domicilio;
import com.example.demo.model.Persona;

@Component
public class PersonaMapper {
	public Persona dtoToEntity(PersonaDTO dto) {
		Persona entity = new Persona();
		
		return entity;
	}
	
	public PersonaDTO entityToDTO(Persona per) {
		PersonaDTO dto = new PersonaDTO();
		dto.setId(per.getId());
		dto.setNombre(per.getNombre());
		dto.setApellido(per.getApellido());
		dto.setDni(per.getDni());
		dto.setDomicilios(new ArrayList<DomicilioDTO>());
		// Stack overflow
		//dto.setDomicilios(per.getDomicilios());

		for(Domicilio d : per.getDomicilios()) {
			DomicilioDTO domDto = new DomicilioDTO();
			domDto.setAltura(d.getAltura());
			domDto.setCalle(d.getCalle());
			domDto.setId(d.getId());
			dto.getDomicilios().add(domDto);
		}
		
		return dto;
	}
	
	
	public List<PersonaDTO> entityToDTO(List<Persona> per) {
		List<PersonaDTO> dto = new ArrayList<PersonaDTO>();
		for(Persona p : per) {
			dto.add(this.entityToDTO(p));
		}
		return dto;
	}
}
