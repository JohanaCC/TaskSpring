package com.persons.manager.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Response {
	private String codigo;
	private String mensaje;
	
	public Response(String code, String message) {
		super();
		this.codigo = code;
		this.mensaje = message;
	}

}
