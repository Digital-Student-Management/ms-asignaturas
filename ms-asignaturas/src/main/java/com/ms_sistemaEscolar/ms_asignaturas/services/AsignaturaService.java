package com.ms_sistemaEscolar.ms_asignaturas.services;

import com.ms_sistemaEscolar.ms_asignaturas.models.dto.AsignaturaDTO;
import com.ms_sistemaEscolar.ms_asignaturas.models.entity.Asignatura;
import com.ms_sistemaEscolar.ms_asignaturas.repositories.AsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AsignaturaService {

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // ── Listar todas ──────────────────────────────────────────────────────────
    public List<AsignaturaDTO> listar() {
        return asignaturaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // ── Buscar por ID ─────────────────────────────────────────────────────────
    public AsignaturaDTO buscarPorId(int id_asignatura) {
        Optional<Asignatura> asignatura = asignaturaRepository.findById(id_asignatura);
        return asignatura.map(this::convertirADTO).orElse(null);
    }

    // ── Guardar (crear / actualizar) ──────────────────────────────────────────
    public AsignaturaDTO guardar(AsignaturaDTO dto) {
        Asignatura asignatura = convertirAEntidad(dto);
        Asignatura guardada = asignaturaRepository.save(asignatura);
        return convertirADTO(guardada);
    }

    // ── Eliminar ──────────────────────────────────────────────────────────────
    public void eliminar(int id_asignatura) {
        asignaturaRepository.deleteById(id_asignatura);
    }

    // ── Mapeo Entity → DTO ────────────────────────────────────────────────────
    private AsignaturaDTO convertirADTO(Asignatura asignatura) {
        AsignaturaDTO dto = new AsignaturaDTO();
        dto.setId_asignatura(asignatura.getId_asignatura());
        dto.setNombre_asignatura(asignatura.getNombre_asignatura());
        dto.setHoras_semanales(asignatura.getHoras_semanales());
        return dto;
    }

    // ── Mapeo DTO → Entity ────────────────────────────────────────────────────
    private Asignatura convertirAEntidad(AsignaturaDTO dto) {
        Asignatura asignatura = new Asignatura();
        asignatura.setId_asignatura(dto.getId_asignatura());
        asignatura.setNombre_asignatura(dto.getNombre_asignatura());
        asignatura.setHoras_semanales(dto.getHoras_semanales());
        return asignatura;
    }

}
