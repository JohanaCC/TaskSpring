package com.manager.dto;

import java.util.List;
import com.manager.model.Cuenta.TipoCuenta;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CuentaDTO {
	private Long id;
	@NotNull
	@Digits(integer = 40, fraction = 0)
    private String nroCuenta;
    private List<MovimientoDTO> movimientos;
    @NotNull
    private TipoCuenta tipoCuenta;
    @NotNull
    private Double saldoInicial;
	private Boolean estado = true;
	private ClienteDTO clienteDTO;
	@NotNull
	private Integer idCliente;
	private String cliente;
}
