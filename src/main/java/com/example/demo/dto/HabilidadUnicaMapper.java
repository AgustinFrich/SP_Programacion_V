package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.model.HabilidadUnica;

@Component
public class HabilidadUnicaMapper {
	public HabilidadUnicaDTO entityToDTO(HabilidadUnica entity) {
		HabilidadUnicaDTO dto = new HabilidadUnicaDTO();
		dto.setNombre(entity.getNombre());
		dto.setColor(entity.getColor());
		dto.setId(entity.getId());
	return dto;
	}
	
	public HabilidadUnica dtoToEntity(HabilidadUnicaDTO dto) {
		HabilidadUnica hab = new HabilidadUnica();
		hab.setNombre(dto.getNombre());
		hab.setColor(dto.getColor());
		hab.setId(dto.getId());
		return hab;
	}
}
