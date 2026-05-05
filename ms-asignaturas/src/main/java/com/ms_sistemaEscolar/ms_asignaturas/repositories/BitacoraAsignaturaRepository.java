package com.ms_sistemaEscolar.ms_asignaturas.repositories;

import com.ms_sistemaEscolar.ms_asignaturas.models.entity.BitacoraAsignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BitacoraAsignaturaRepository extends JpaRepository<BitacoraAsignatura, Integer> {

    @Query("SELECT b FROM BitacoraAsignatura b WHERE b.asignatura.id_asignatura = :idAsignatura")
    List<BitacoraAsignatura> findByAsignatura_IdAsignatura(@Param("idAsignatura") int id_asignatura);

}
