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
	@NotNull
    private String nroCuenta;
    @NotNull
    private TipoCuenta tipoCuenta;
    @NotNull
    private Double saldoInicial;
	private Boolean estado;
	private String cliente;
	private Date movimientoFecha;
	private Double saldo;
	private Double valor;
}
