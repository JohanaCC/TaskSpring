package com.persons.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.persons.manager.model.Cliente;

@Repository
public interface IClienteRep extends JpaRepository<Cliente, Integer> {

	Optional<Cliente> findByIdentificacion(String identificacion);
	
	public List<Cliente> findByNombreIgnoreCase(String nombre);
	
	Optional<Cliente> findByNombre(String nombre);
	
	Optional<Cliente> findById(Integer id);
}
