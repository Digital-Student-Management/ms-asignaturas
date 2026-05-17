package com.ms_sistemaEscolar.ms_asignaturas.repositories;

import com.ms_sistemaEscolar.ms_asignaturas.models.entity.Asignatura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsignaturaRepository extends JpaRepository<Asignatura, Integer> {

}
