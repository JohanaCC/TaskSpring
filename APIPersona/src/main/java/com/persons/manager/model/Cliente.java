package com.persons.manager.model;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@PrimaryKeyJoinColumn(name = "clienteid")
public class Cliente extends Persona{
	
	public Cliente() {
		
	}
	public Cliente(String identificacion, String nombre, String genero, int edad, String direccion,
			String telefono) {
		super(identificacion, nombre, genero, edad, direccion, telefono);
		// TODO Auto-generated constructor stub
	}
	
	@Column(name = "clave")
	private String contraseña;
	private Boolean estado;

	
	public void setContraseña(String contraseña) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(contraseña);
        this.contraseña = hashedPassword;
    }

    public boolean verificarContraseña(String contraseña) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(contraseña, this.contraseña);
    }
    
}
