package com.manager.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manager.model.Cuenta;

@Repository
public interface ICuentaRep extends JpaRepository<Cuenta, Long> {

	Optional<Cuenta> findByNroCuenta(String nroCuenta);
	
	Optional<Cuenta> findById(Long id);
	
	Optional<Cuenta> findByClienteId(Integer idCliente);
}
