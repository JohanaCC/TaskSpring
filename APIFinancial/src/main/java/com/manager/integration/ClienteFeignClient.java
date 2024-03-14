package com.manager.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.manager.dto.ClienteDTO;

@FeignClient(name = "cliente-service", url = "http://localhost:2000")
public interface ClienteFeignClient {

    @GetMapping("/clientes/{clienteId}")
    ClienteDTO obtenerClientePorId(@PathVariable("clienteId") int clienteId);
    
    @GetMapping("/clientes/{nombre}")
    ClienteDTO obtenerClientePorNombre(@PathVariable("clienteId") String nombre);
}
