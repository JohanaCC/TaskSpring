package com.manager.integration;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.manager.config.FeignClientConfig;
import com.manager.dto.ResponseCliente;

@FeignClient(name = "java-api-person", url = "${microservicio_persona}", configuration = FeignClientConfig.class )
public interface ClienteFeignClient {

	@GetMapping(value="/clientes", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseCliente obtenerClientes();
	
	@GetMapping(value="/clientes/{clienteId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseCliente obtenerClientePorId(@PathVariable("clienteId") int clienteId);
    
    @GetMapping(value="/clientes/{nombre}", consumes = MediaType.APPLICATION_JSON_VALUE)
    ResponseCliente obtenerClientePorNombre(@PathVariable("clienteId") String nombre);
}
