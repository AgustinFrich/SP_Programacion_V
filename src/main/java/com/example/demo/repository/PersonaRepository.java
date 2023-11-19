package com.example.demo.repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Persona;
import java.util.List;


@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {
	List<Persona> findAllByApellido(String apellido);
	List<Persona> findAllByApellidoAndNombreOrderById(String apellido, String nombre);
	
	
	@Query("select p from Persona p " 
			+ "inner join p.domicilios d "
			+ "where p.apellido =:lastName "
			+ "and d.calle =:calle")
	List<Persona> buscarPorDatos(String lastName, String calle);
	
	// Otro tipo de queris: 
	// @Transactional
	// @Modifying
}
