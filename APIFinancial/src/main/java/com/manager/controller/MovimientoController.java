package com.manager.controller;

import com.manager.dto.MovimientoDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseMovimiento;
import com.manager.service.IMovimientoService;
import com.manager.util.Constants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/movimientos")
public class MovimientoController {

    @Autowired
    private IMovimientoService movimientoService;

    @GetMapping
    public ResponseEntity<ResponseMovimiento> getAllMovimientos() {
        return new ResponseEntity<ResponseMovimiento>(movimientoService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/{cuentaId}")
    public ResponseEntity<ResponseMovimiento> getByCuenta(@PathVariable("cuentaId") Long cuentaId) {
        return new ResponseEntity<ResponseMovimiento>(movimientoService.getByCuenta(cuentaId), HttpStatus.OK);
    }
    
    @PostMapping
    public ResponseEntity<Response> crearMovimiento(@RequestBody MovimientoDTO Movimiento) {
        return new ResponseEntity<Response>(movimientoService.crear(Movimiento), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Response> actualizar(@RequestBody MovimientoDTO movimientoDTO) {
        return new ResponseEntity<Response>(movimientoService.actualizarUltimoMovimiento(movimientoDTO, Constants.UPDATE_PUT), HttpStatus.OK);
    }
    
    @PatchMapping
    public ResponseEntity<Response> actualizarParcial(@RequestBody MovimientoDTO movimientoDTO) {
        return new ResponseEntity<Response>(movimientoService.actualizarUltimoMovimiento(movimientoDTO, Constants.UPDATE_PATCH), HttpStatus.OK);
    }

    @DeleteMapping("/{movimientoid}")
    public ResponseEntity<Response> eliminar(@PathVariable("movimientoid") Long movimientoid) {
        return new ResponseEntity<Response>(movimientoService.eliminar(movimientoid), HttpStatus.OK);
    }
}
