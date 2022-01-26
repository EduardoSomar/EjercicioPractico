package com.example.ejercicio.persistence;

import com.example.ejercicio.persistence.crud.ProspectoCrudRepository;
import com.example.ejercicio.persistence.entity.Prospecto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProspectoRepository{
    private ProspectoCrudRepository prospectoCrudRepository;


    public List<Prospecto> getAll(){
        return (List<Prospecto>) this.prospectoCrudRepository.findAll();
    }
    public List<Prospecto> getById(int id) {
        return prospectoCrudRepository.findById(id);
    }

    public Optional<List<Prospecto>> getByEstatus(String Estatus){
        return prospectoCrudRepository.findByEstatus(Estatus);
    }
}
