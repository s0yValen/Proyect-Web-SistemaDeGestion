package com.proyecto.sistemagestion.services;

import com.proyecto.sistemagestion.entities.Empresa;
import com.proyecto.sistemagestion.repositories.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired //inyecta la dependencia EmpresaRepository creando el objeto
    EmpresaRepository empresaRepository;

    public List<Empresa> getAllEmpresas(){
        List<Empresa> listaEmpresas = new ArrayList<>();
        empresaRepository.findAll().forEach(empresa -> listaEmpresas.add(empresa));
        return  listaEmpresas;
    }

    public Empresa getEmpresaById(Integer id){
        return empresaRepository.findById(id).get();

    }
    public boolean saveOrUpdateEmpresa(Empresa empresa){
        Empresa emp=empresaRepository.save(empresa);
        if (empresaRepository.findById(emp.getId())!=null){
            return true;
        }
        return false;
    }
    public boolean deleteEmpresa (Integer id){
        empresaRepository.deleteById(id);
        if(empresaRepository.findById(id)!=null){
            return true;

        }
        return false;

    }


}
