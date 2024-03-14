package com.manager.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.manager.model.Movimiento;


@Repository
public interface IMovimientoRep extends JpaRepository<Movimiento, Long> {
	
	Optional<Movimiento> findById(Long id);
	
	List<Movimiento> findByTipoMovimiento(String tipoMovimiento);
	
	List<Movimiento> findByCuenta(Long cuenta);

	@Query("SELECT m FROM Movimiento m WHERE m.cuenta = :cuenta ORDER BY m.fecha DESC, m.id DESC")
    Optional<Movimiento> findTopByCuentaOrderByFechaDescIdDesc(Long cuenta);
	
	@Query(value =  "SELECT * FROM movimiento WHERE cuenta_id = :cuentaId ORDER BY fecha DESC, id DESC LIMIT 1", nativeQuery = true)
	Optional<Movimiento> findLastMovimientoByCuentaId(Long cuentaId);
	
	List<Movimiento> findByDateBetween(Date startDate, Date endDate);
}
