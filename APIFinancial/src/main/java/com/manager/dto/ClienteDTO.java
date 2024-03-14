package com.manager.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ClienteDTO {
	private Integer id;
    private String identificacion;
    @NotBlank(message = "El nombre no puede estar en blanco")
	private String nombre;
    private String genero;
    private Integer edad;
    private String direccion;
    private String telefono;
    private String clave;
    private boolean estado;
}
