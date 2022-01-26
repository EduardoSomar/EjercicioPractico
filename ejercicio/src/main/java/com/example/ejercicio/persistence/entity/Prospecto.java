package com.example.ejercicio.persistence.entity;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="prospecto")
public class Prospecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String primerapellido;

    private String segundoapellido;

    private String calle;

    private String numero;

    private String colonia;

    private String codigopostal;

    private String telefono;

    private String rfc;

    private String estatus;

    private String observaciones;

}
