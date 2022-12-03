package com.proyecto.sistemagestion.repositories;

import com.proyecto.sistemagestion.entities.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface MovimientoDineroRepository extends JpaRepository<MovimientoDinero,Integer> {
    @Query(value = "SELECT * FROM movimiento WHERE usuario_id = ?1", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpleado(Integer id);

    @Query(value = "SELECT * FROM movimiento WHERE usuario_id IN(SELECT id FROM empleado  WHERE empresa_id = ?1)", nativeQuery = true)
    public abstract ArrayList<MovimientoDinero> findByEmpresa(Integer id);

    @Query(value = "SELECT id FROM empleado WHERE correo = ?1", nativeQuery = true)
    public abstract Integer idByCorreo(String correo);

    @Query(value ="SELECT SUM(monto) from Movimiento",nativeQuery = true)
    public abstract long sumarMonto();
}
