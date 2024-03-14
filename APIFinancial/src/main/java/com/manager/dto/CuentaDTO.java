package com.manager.dto;

import java.util.List;
import com.manager.model.Cuenta.TipoCuenta;
import lombok.Data;

@Data
public class CuentaDTO {
	private Long id;
    private String nroCuenta;
    private List<MovimientoDTO> movimientos;
    private TipoCuenta tipoCuenta;
    private Double saldoInicial;
	private Boolean estado;
	private ClienteDTO clienteDTO;
	private Integer idCliente;
	private String cliente;
}
