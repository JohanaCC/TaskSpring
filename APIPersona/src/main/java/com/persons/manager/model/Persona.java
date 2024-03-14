package com.persons.manager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Data;

@Data
@Entity
@Inheritance( strategy = InheritanceType.JOINED)
public class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id; 
	
	private String identificacion;
	
	private String nombre;
	
	private String genero;
	
	private int edad;
	
	private String direccion;
	

	private String telefono;

	public Persona(String identificacion, String nombre, String genero, int edad, String direccion,
			String telefono) {
		super();
		this.identificacion = identificacion;
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.direccion = direccion;
		this.telefono = telefono;
	}
	public Persona() {}
	
}
