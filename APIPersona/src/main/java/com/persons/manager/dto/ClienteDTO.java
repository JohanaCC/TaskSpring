package com.persons.manager.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ClienteDTO {
	private Integer id;
    private String identificacion;
    @NotBlank(message = "El nombre no puede estar en blanco")
    @NotNull(message = "El cliente debe tener un nombre")
	private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private String clave;
 // Valor por defecto para el campo estado
    private Boolean estado = true;
}
