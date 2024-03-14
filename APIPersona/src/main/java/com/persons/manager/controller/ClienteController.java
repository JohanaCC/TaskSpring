package com.persons.manager.controller;

import com.persons.manager.dto.ClienteDTO;
import com.persons.manager.dto.Response;
import com.persons.manager.dto.ResponseCliente;
import com.persons.manager.service.IClienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private IClienteService clienteService;

   
    @GetMapping
    public ResponseEntity<ResponseCliente> getAllClientes() {
        return new ResponseEntity<ResponseCliente>(clienteService.getAll(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Response> crearCliente(@RequestBody ClienteDTO Cliente) {
        return new ResponseEntity<Response>(clienteService.crear(Cliente), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<Response>(clienteService.actualizar(clienteDTO), HttpStatus.OK);
    }
    
    @PatchMapping
    public ResponseEntity<Response> actualizarParcial(@RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<Response>(clienteService.actualizarParcial(clienteDTO), HttpStatus.OK);
    }
    
    @PutMapping("/{nombre}")
    public ResponseEntity<Response> actualizarPorNombre(@PathVariable String nombre, @RequestBody ClienteDTO clienteDTO) {
        return new ResponseEntity<Response>(clienteService.actualizarPorNombre(nombre, clienteDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{clienteid}")
    public ResponseEntity<Response> eliminar(@PathVariable("clienteid") Integer clienteid) {
        return new ResponseEntity<Response>(clienteService.eliminar(clienteid), HttpStatus.OK);
    }
    
    @GetMapping("/clientes/{clienteId}")
    public ResponseEntity<ResponseCliente> getClienteById(@PathVariable("clienteId") int clienteId) {
    	return new ResponseEntity<ResponseCliente>(clienteService.getAll(), HttpStatus.OK);
    }
    
    @GetMapping("/clientes/{nombre}")
    public ResponseEntity<ResponseCliente> getClienteByNombre(@PathVariable("clienteId") String nombre) {
    	return new ResponseEntity<ResponseCliente>(clienteService.getAll(), HttpStatus.OK);
    }
}
