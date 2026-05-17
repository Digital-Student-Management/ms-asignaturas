package com.ms_sistemaEscolar.ms_asignaturas.models.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "bitacora_asignatura")
public class BitacoraAsignatura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_bitacora_asignatura;

    private Date fecha_clase;

    private String actividades_realizadas;

    private String observaciones_generales;

    private String objetivo_aprendizaje;

    @ManyToOne
    @JoinColumn(name = "id_asignatura")
    private Asignatura asignatura;

}
