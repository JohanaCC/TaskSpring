package com.persons.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persons.manager.model.Persona;


@Repository
public interface IPersonaRep extends JpaRepository<Persona, Integer> {
	
	Optional<Persona> findByIdentificacion(String identificacion);
	
	Optional<Persona> findByNombre(String nombre);
	
	Optional<Persona> findById(Integer id);

}
