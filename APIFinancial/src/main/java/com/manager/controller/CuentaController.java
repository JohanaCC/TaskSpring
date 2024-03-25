package com.manager.controller;

import com.manager.dto.CuentaDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseCliente;
import com.manager.dto.ResponseCuenta;
import com.manager.service.ICuentaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cuentas")
public class CuentaController {

    @Autowired
    private ICuentaService cuentaService;

   
    @GetMapping
    public ResponseEntity<ResponseCuenta> getAllCuentas() {
        return new ResponseEntity<ResponseCuenta>(cuentaService.obtenerTodas(), HttpStatus.OK);
    }
    
    @GetMapping("/clientes")
    public ResponseEntity<ResponseCliente> getAllClientes() {
        return new ResponseEntity<ResponseCliente>(cuentaService.obtenerTodosClientes(), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Response> crearCuenta(@RequestBody CuentaDTO Cuenta) {
        return new ResponseEntity<Response>(cuentaService.crear(Cuenta), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@RequestBody CuentaDTO cuentaDTO) {
        return new ResponseEntity<Response>(cuentaService.actualizar(cuentaDTO), HttpStatus.OK);
    }
    
    @PatchMapping
    public ResponseEntity<Response> actualizarParcial(@RequestBody CuentaDTO cuentaDTO) {
        return new ResponseEntity<Response>(cuentaService.actualizarParcial(cuentaDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{cuentaid}")
    public ResponseEntity<Response> eliminar(@PathVariable("cuentaid") Long cuentaid) {
        return new ResponseEntity<Response>(cuentaService.eliminar(cuentaid), HttpStatus.OK);
    }
}
