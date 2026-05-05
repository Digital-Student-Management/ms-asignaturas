package com.ms_sistemaEscolar.ms_asignaturas.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "asignatura")
public class Asignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_asignatura;

    @Column(nullable = false)
    private String nombre_asignatura;

    private int horas_semanales;

    @OneToMany(mappedBy = "asignatura")
    private List<BitacoraAsignatura> bitacoras;

}
