package com.persons.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.persons.manager.model.Persona;
import com.persons.manager.repository.IPersonaRep;

import jakarta.persistence.PersistenceException;

@Service
public class PersonaServiceImpl implements IPersonaService {
	
	@Autowired
    private IPersonaRep personaRepository;

	@Override
	public Persona crear(Persona persona) {
		 try {
			 personaRepository.save(persona);
			 } 
		 catch (PersistenceException e) {
            throw new RuntimeException("Ya existe una persona con el mismo nombre e identificación");
        }
		
        return persona;
	}
	


}
