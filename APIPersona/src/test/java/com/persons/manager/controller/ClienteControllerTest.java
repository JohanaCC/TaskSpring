package com.persons.manager.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.persons.manager.dto.ClienteDTO;
import com.persons.manager.dto.Response;
import com.persons.manager.dto.ResponseCliente;
import com.persons.manager.service.IClienteService;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private IClienteService clienteService;

    @Test
    public void testCrearCliente() {
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificacion("0107484966");
        clienteDTO.setTelefono("0980893644");
        Response response = new Response();
        when(clienteService.crear(clienteDTO)).thenReturn(response);

        ResponseEntity<Response> responseEntity = clienteController.crearCliente(clienteDTO);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(response, responseEntity.getBody());
    }

    @Test
    public void testGetAllClientes() {
        ResponseCliente responseCliente = new ResponseCliente();
        when(clienteService.getAll()).thenReturn(responseCliente);

        ResponseEntity<ResponseCliente> response = clienteController.getAllClientes();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(responseCliente, response.getBody());
    }

    

}
