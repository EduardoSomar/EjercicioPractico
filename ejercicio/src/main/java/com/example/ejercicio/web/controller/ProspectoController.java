package com.example.ejercicio.web.controller;

import com.example.ejercicio.domain.service.ProspectoService;
import com.example.ejercicio.persistence.entity.Prospecto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.PostUpdate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prospecto")
public class ProspectoController {
    @Autowired
    private ProspectoService prospectoService;

    @GetMapping("/all")
    public List<Prospecto> getAll(){
        return prospectoService.getAll();
    }

    @GetMapping("/id/{id}")
    public List<Prospecto> getById(@PathVariable("id") Integer id){
        return prospectoService.getById(id);
    }

    @GetMapping("/estatus/{estatus}")
    public Optional<List<Prospecto>> getByEstatus(@PathVariable("estatus") String Estatus){
        return prospectoService.getByEstatus(Estatus);
    }

    @PostMapping("/save")
    public Prospecto save(@RequestBody Prospecto prospecto){
        return prospectoService.save(prospecto);
    }


    @PutMapping("/revisar")
    public Prospecto update(@RequestBody Prospecto prospecto){
        return prospectoService.update(prospecto);
    }


}
