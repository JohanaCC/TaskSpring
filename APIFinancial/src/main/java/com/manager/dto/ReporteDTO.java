package com.manager.dto;

import java.util.Date;
import java.util.List;
import com.manager.model.Cuenta.TipoCuenta;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteDTO {
	private Long id;
    private String nroCuenta;
    private List<MovimientoDTO> movimientos;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
	private Boolean estado;
	private ClienteDTO clienteDTO;
	private Integer idCliente;
	private String cliente;
	private Date movimientoFecha;
	private Double saldo;
	private Double valor;
}
