package com.example.ejercicio.persistence.crud;

import com.example.ejercicio.persistence.entity.Prospecto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProspectoCrudRepository extends CrudRepository<Prospecto, Integer> {
    List<Prospecto> findById(int id);

    Optional<List<Prospecto>> findByEstatus(String Estatus);
}
