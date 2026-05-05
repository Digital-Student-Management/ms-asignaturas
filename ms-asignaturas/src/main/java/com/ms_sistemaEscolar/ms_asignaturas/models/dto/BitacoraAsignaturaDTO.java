package com.ms_sistemaEscolar.ms_asignaturas.models.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class BitacoraAsignaturaDTO {

    private int id_bitacora_asignatura;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "UTC")
    private Date fecha_clase;

    private String actividades_realizadas;

    private String observaciones_generales;

    private String objetivo_aprendizaje;

    private int id_asignatura;

}
