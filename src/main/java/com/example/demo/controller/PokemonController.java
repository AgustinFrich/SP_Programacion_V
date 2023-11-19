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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.PokemonDTO;
import com.example.demo.dto.PokemonMapper;
import com.example.demo.model.Pokemon;
import com.example.demo.model.HabilidadUnica;
import com.example.demo.repository.PokemonRepository;

@RestController
@RequestMapping("/api/pokemon")
public class PokemonController {
	@Autowired PokemonRepository pokemonRepository;

	@Autowired PokemonMapper pokemonMapper;
	
	@GetMapping("/nombre_ataque")
	public ResponseEntity<?> traerPokemonPorNombreYAtaque(@RequestParam String nombre, @RequestParam Long ataque) {
		try {
			List<Pokemon> entity = pokemonRepository.findAllByAtaqueAndNombre(ataque, nombre);
		List<PokemonDTO> listDTO = pokemonMapper.entityToDTO(entity);
			return new ResponseEntity<List<PokemonDTO>>(listDTO, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Pokemon no encontrado en nombre = " + nombre, HttpStatus.CONFLICT);
		}
	}
	
	
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<?> traerPokemonYHabilidadesPorNombre(@PathVariable String nombre) {
		try {
			List<Pokemon> entity = pokemonRepository.buscarPokemonYHabsPorNombre(nombre);
		List<PokemonDTO> listDTO = pokemonMapper.entityToDTO(entity);
			return new ResponseEntity<List<PokemonDTO>>(listDTO, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("Pokemon no encontrado en nombre = " + nombre, HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping("/stats")
	public ResponseEntity<?> traerHabilidadPorID(@RequestParam Integer ataque, @RequestParam Integer defensa) {
		try {
			List<Pokemon> entity = pokemonRepository.buscarPokemonPorStats(ataque, defensa);
			if(entity.size() <= 0) {
				throw new Exception();
			}
			List<PokemonDTO> dto = pokemonMapper.entityToDTO(entity);
			return new ResponseEntity<List<PokemonDTO>>(dto, HttpStatus.OK);
		} catch(Exception e) {
			return new ResponseEntity<String>("No hay pokemons mas fuertes que = " + ataque + " y con mejor defensa que = " + defensa, HttpStatus.CONFLICT);
		}
	}
	
	// TRAER POR ID
	@GetMapping("/{id}")
	public ResponseEntity<?> traerPokemon(@PathVariable Long id) {
		Optional<Pokemon> entityOpt = pokemonRepository.findById(id);
		
		if(entityOpt.isPresent()) {
			PokemonDTO dto = pokemonMapper.entityToDTO(entityOpt.get());
			return new ResponseEntity<PokemonDTO>(dto, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Pokemon no encontrado en id = " + id, HttpStatus.CONFLICT);
		}
	}
	
	@PostMapping("/")
	public ResponseEntity<?> crearPokemon(@RequestBody @Validated PokemonDTO dto) {
		try {
			Pokemon p = pokemonMapper.dtoToEntitySinRecursividad(dto);
			
			pokemonRepository.save(p);
			return new ResponseEntity<String>("Guardado correctamente", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("No se pudo guardar : " + e.getMessage(), HttpStatus.BAD_REQUEST);			
		}
	}
	
	@PutMapping("/")
	public ResponseEntity<?> modificarPokemon(@RequestBody @Validated PokemonDTO dto) {
		Optional<Pokemon> entityOpt = pokemonRepository.findById(dto.getId());
		
		if(entityOpt.isPresent()) {
			Pokemon p = pokemonMapper.dtoToEntity(dto);
			
			for(HabilidadUnica h: p.getHabilidades()) {
				h.setPokemon(p);
			}
			
			pokemonRepository.save(p);
			return new ResponseEntity<String>("Modificado correctamente", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Pokemon no encontrado en id = " + dto.getId(), HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/")
	public ResponseEntity<?> eliminarPokemon(@RequestBody @Validated PokemonDTO dto) {
		try {			
			Pokemon pkmn = pokemonMapper.dtoToEntity(dto);
			pokemonRepository.delete(pkmn);
			return new ResponseEntity<String>("Borrado correctamente", HttpStatus.OK);
		} catch(IllegalArgumentException e) {
			return new ResponseEntity<String>("No se pudo borrar", HttpStatus.CONFLICT);
		}
	}
}
