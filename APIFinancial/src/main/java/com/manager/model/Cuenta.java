package com.manager.model;

import java.util.List;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.Data;

@Data
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"numero", "tipo"})})
public class Cuenta {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "numero")
	private String nroCuenta;
	
	@Column(name = "tipo")
	@Enumerated(EnumType.STRING)
	private TipoCuenta tipoCuenta;
	
	@Column(name = "saldo_inicial")
	private Double saldoInicial;
	
	private Boolean estado;
	
	@Column(name = "id_cliente")
	private int clienteId;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY, mappedBy = "cuenta")
	@Fetch(value = FetchMode.SUBSELECT)
    private List<Movimiento> movimientos;
	
	public enum TipoCuenta {
        AHORROS,
        CORRIENTE,
    }
	 
}
