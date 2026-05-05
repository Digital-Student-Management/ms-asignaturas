package com.ms_sistemaEscolar.ms_asignaturas.repositories;

import com.ms_sistemaEscolar.ms_asignaturas.models.entity.BitacoraAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraAsignaturaRepository extends JpaRepository<BitacoraAsignatura, Integer> {

    List<BitacoraAsignatura> findByAsignatura_IdAsignatura(int id_asignatura);

}
