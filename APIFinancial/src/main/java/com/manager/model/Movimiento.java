package com.manager.model;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Movimiento {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "cuenta_id", foreignKey = @ForeignKey(name = "FK_cuenta_mov"), nullable = false)
    private Cuenta cuenta;
    
	private Date fecha;
	
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoMovimiento tipoMovimiento;
	
	private Double valor;
	
	@Column(name = "saldo_post")
	private Double saldo;
	
	@Column(name = "saldo_pre")
	private Double saldoAnterior;
	
	private Boolean estado;
	
	
	public enum TipoMovimiento {
        DEPOSITO,
        RETIRO,
    }
}
