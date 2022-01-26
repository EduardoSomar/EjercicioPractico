package com.example.ejercicio.domain.service;

import com.example.ejercicio.persistence.ProspectoRepository;
import com.example.ejercicio.persistence.crud.ProspectoCrudRepository;
import com.example.ejercicio.persistence.entity.Prospecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProspectoService {

    @Autowired
    private ProspectoCrudRepository prospectoCrudRepository;

    public List<Prospecto> getAll(){
        return (List<Prospecto>) this.prospectoCrudRepository.findAll();
    }

    public List<Prospecto> getById(int id){
        return this.prospectoCrudRepository.findById(id);
    }

    public Optional<List<Prospecto>> getByEstatus(String Estatus){
        return this.prospectoCrudRepository.findByEstatus(Estatus);
    }

    public Prospecto save(Prospecto prospecto){
        return this.prospectoCrudRepository.save(prospecto);
    }

    public Prospecto update(Prospecto prospecto) {
        return this.prospectoCrudRepository.save(prospecto);
    }
}
