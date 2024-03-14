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
	private String code;
	private String message;
	
	public Response(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
