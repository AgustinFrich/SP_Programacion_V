package com.example.demo.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.HabilidadUnica;
import com.example.demo.model.Pokemon;
import java.util.List;


@Repository
public interface PokemonRepository extends CrudRepository<Pokemon, Long> {
	List<Pokemon> findAllByAtaqueAndNombre(Long ataque, String nombre);
	
	// Obtiene las habilidades por el nombre del pkmn
	@Query("select pkmn from Pokemon pkmn " 
			+ "inner join pkmn.habilidades h "
			+ "where pkmn.nombre =:nombre ")
	List<Pokemon> buscarPokemonYHabsPorNombre(String nombre);
	
	@Query(nativeQuery = true, value = "SELECT * FROM sys_pokemones WHERE pkmn_ataque > :ataque AND pkmn_defensa > :defensa")
	List<Pokemon> buscarPokemonPorStats(Integer ataque, Integer defensa);
}
