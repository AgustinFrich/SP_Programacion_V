package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PersonaDTO;
import com.example.demo.dto.PersonaMapper;
import com.example.demo.model.Domicilio;
import com.example.demo.model.Persona;
import com.example.demo.repository.PersonaRepository;

@RestController
@RequestMapping("/api/persona")
public class PersonaController {
	@Autowired
	PersonaRepository personaRepository;
	
	@Autowired
	PersonaMapper personaMapper;
	
	@GetMapping("/saludar")
	public String saludar() {
		return "hola";
	}
	
	@GetMapping({"/{id}", "/"})
	public ResponseEntity<?> obtenerPersona(@PathVariable(required = false) Long id) {
		if(id == null) {
			List<Persona> prs = (List<Persona>) personaRepository.findAll();
			
			List<PersonaDTO> prsDTO = personaMapper.entityToDTO(prs);
			
			return new ResponseEntity<List<PersonaDTO>>(prsDTO, HttpStatus.OK);						
		}
		Optional<Persona> pOpt = personaRepository.findById(id);
		
		if(pOpt.isPresent() ) {
			return new ResponseEntity<Persona>(pOpt.get(), HttpStatus.OK);			
		}else {
			return new ResponseEntity<String>("Persona no encontrada", HttpStatus.OK);			
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> agregarPersona(@RequestBody @Validated Persona p) {
		
		List<Domicilio> doms = p.getDomicilios();
		int s = doms.size();
		Persona tmp = new Persona();
		tmp.setNombre(p.getNombre());
		tmp.setApellido(p.getApellido());
		tmp.setDni(p.getDni());
		tmp.setId(p.getId());
		
		/*
		 * Si trato de agregar la persona al domicilio sin sacar sus domicilios se vuelve recursivo y explota todo
		 * */
		
		for (int i = 0; i < s; i++) {
			doms.get(i).persona = tmp;
		}
		personaRepository.save(p);
		return new ResponseEntity<Persona>(p, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> modificarPersona(@RequestBody @Validated PersonaDTO p) {
		Optional<Persona> per = personaRepository.findById(p.getId());
		if(per.isPresent()) {
			personaRepository.save(per.get());
			return new ResponseEntity<Persona>(per.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Nada para actualizar", HttpStatus.CONFLICT);			
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> borrarPersona(@RequestBody @Validated Persona p){
		try {			
			personaRepository.delete(p);
			return new ResponseEntity<String>("Borrado correctamente", HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<String>("No se pudo borrar", HttpStatus.CONFLICT);
		}

	}
	
}
