package com.manager.dto;

import java.util.Date;
import java.util.List;
import com.manager.model.Cuenta.TipoCuenta;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReporteDTO {
	private Long id;
	@NotNull
    private String nroCuenta;
    private List<MovimientoDTO> movimientos;
    @NotNull
    private TipoCuenta tipoCuenta;
    @NotNull
    private Double saldoInicial;
	private Boolean estado;
	private ClienteDTO clienteDTO;
	private Integer idCliente;
	private String cliente;
	private Date movimientoFecha;
	private Double saldo;
	private Double valor;
}
