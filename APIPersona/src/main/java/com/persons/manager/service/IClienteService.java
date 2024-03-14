package com.persons.manager.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.persons.manager.dto.ClienteDTO;
import com.persons.manager.dto.Response;
import com.persons.manager.dto.ResponseCliente;

public interface IClienteService {
	
	public Response crear(ClienteDTO clienteDTO);
	
	public Response actualizar(ClienteDTO clienteDTO);
	
	public Response actualizarParcial(ClienteDTO clienteDTO);
	
	public Response actualizar(ClienteDTO clienteActualizadoDTO, Integer id);
	
	public Response actualizarPorIdentificacion(String identificacion, ClienteDTO clienteActualizadoDTO);
	
	public Response actualizarPorNombre(String nombre, ClienteDTO clienteActualizadoDTO);

	public Response eliminar(Integer clienteid);

	public ResponseCliente getAll();
	
    public ResponseCliente getClienteById(int clienteId);
    
    public ResponseCliente getClienteByNombre(String nombre);

	
	
}
