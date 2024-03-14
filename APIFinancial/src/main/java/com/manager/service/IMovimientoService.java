package com.manager.service;


import com.manager.dto.MovimientoDTO;
import com.manager.dto.Response;
import com.manager.dto.ResponseMovimiento;

public interface IMovimientoService {

    
	public Response crear(MovimientoDTO movimientoDTO);
	
	public Response actualizarUltimoMovimiento(MovimientoDTO movimientoActualizadoDTO, int tipo);
	
	public Response eliminar(Long movimientoid);

	public ResponseMovimiento getAll();
	
	public ResponseMovimiento getByCuenta(Long idCuenta);

}
