package com.manager.service;

import com.manager.dto.CuentaDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseCliente;
import com.manager.dto.ResponseCuenta;

public interface ICuentaService {
	
	public Response crear(CuentaDTO cuentaDTO);
	
	public Response actualizar(CuentaDTO cuentaDTO);
	
	public Response actualizarParcial(CuentaDTO cuentaActualizadoDTO);
	
	public Response actualizar(CuentaDTO cuentaActualizadoDTO, Long id);
	
	public Response obtenerPorNumero(String identificacion);
	
	public Response obtenerCuentaConCliente(Integer clienteId);
	
	public Response eliminar(Long cuentaid);

	public ResponseCuenta obtenerTodas();
	
	public ResponseCliente obtenerTodosClientes();

	
	
}
