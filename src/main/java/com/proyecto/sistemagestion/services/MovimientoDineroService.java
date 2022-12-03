package com.proyecto.sistemagestion.services;

import com.proyecto.sistemagestion.entities.Empresa;
import com.proyecto.sistemagestion.entities.MovimientoDinero;
import com.proyecto.sistemagestion.repositories.MovimientoDineroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDineroService {

    @Autowired
    MovimientoDineroRepository movimientoRepository;

    public List<MovimientoDinero>  getMovimientosDinero(){
        List<MovimientoDinero> movimientosList = new ArrayList<>();
        movimientoRepository.findAll().forEach(movimiento -> movimientosList.add(movimiento));
        return movimientosList;
    }

    public MovimientoDinero getMovimientoById(Integer id){
        return movimientoRepository.findById(id).get();
    }

    public boolean saveUpdateMovimiento(MovimientoDinero movimiento){
        MovimientoDinero temp = movimientoRepository.save(movimiento);
        if(movimientoRepository.findById(temp.getId()) != null){
            return true;
        } else{
            return false;
        }
    }

    public boolean deleteMovimientoById(Integer id){
        movimientoRepository.deleteById(id);
        if(movimientoRepository.findById(id)!= null){
            return true;
        } else{
            return false;
        }
    }

    public Integer idByCorreo(String correo){
        return movimientoRepository.idByCorreo(correo);
    }

    public ArrayList<MovimientoDinero> getMovimientosByEmpleado(Integer id){
        return movimientoRepository.findByEmpleado(id);
    }
    public ArrayList<MovimientoDinero> getMovimientosByEmpresa(Integer id){
        return movimientoRepository.findByEmpresa(id);
    }

    public Long obtenerSumaMonto(){
        return movimientoRepository.sumarMonto();
    }

}
