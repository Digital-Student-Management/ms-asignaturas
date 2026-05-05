package com.ms_sistemaEscolar.ms_asignaturas.services;

import com.ms_sistemaEscolar.ms_asignaturas.models.dto.BitacoraAsignaturaDTO;
import com.ms_sistemaEscolar.ms_asignaturas.models.entity.Asignatura;
import com.ms_sistemaEscolar.ms_asignaturas.models.entity.BitacoraAsignatura;
import com.ms_sistemaEscolar.ms_asignaturas.repositories.AsignaturaRepository;
import com.ms_sistemaEscolar.ms_asignaturas.repositories.BitacoraAsignaturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BitacoraAsignaturaService {

    @Autowired
    private BitacoraAsignaturaRepository bitacoraAsignaturaRepository;

    @Autowired
    private AsignaturaRepository asignaturaRepository;

    // ── Listar todas ──────────────────────────────────────────────────────────
    public List<BitacoraAsignaturaDTO> listar() {
        return bitacoraAsignaturaRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // ── Buscar por ID ─────────────────────────────────────────────────────────
    public BitacoraAsignaturaDTO buscarPorId(int id_bitacora_asignatura) {
        Optional<BitacoraAsignatura> bitacora = bitacoraAsignaturaRepository.findById(id_bitacora_asignatura);
        return bitacora.map(this::convertirADTO).orElse(null);
    }

    // ── Listar por Asignatura ─────────────────────────────────────────────────
    public List<BitacoraAsignaturaDTO> listarPorAsignatura(int id_asignatura) {
        return bitacoraAsignaturaRepository.findByAsignatura_IdAsignatura(id_asignatura)
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    // ── Guardar (crear / actualizar) ──────────────────────────────────────────
    public BitacoraAsignaturaDTO guardar(BitacoraAsignaturaDTO dto) {
        BitacoraAsignatura bitacora = convertirAEntidad(dto);
        BitacoraAsignatura guardada = bitacoraAsignaturaRepository.save(bitacora);
        return convertirADTO(guardada);
    }

    // ── Eliminar ──────────────────────────────────────────────────────────────
    public void eliminar(int id_bitacora_asignatura) {
        bitacoraAsignaturaRepository.deleteById(id_bitacora_asignatura);
    }

    // ── Mapeo Entity → DTO ────────────────────────────────────────────────────
    private BitacoraAsignaturaDTO convertirADTO(BitacoraAsignatura bitacora) {
        BitacoraAsignaturaDTO dto = new BitacoraAsignaturaDTO();
        dto.setId_bitacora_asignatura(bitacora.getId_bitacora_asignatura());
        dto.setFecha_clase(bitacora.getFecha_clase());
        dto.setActividades_realizadas(bitacora.getActividades_realizadas());
        dto.setObservaciones_generales(bitacora.getObservaciones_generales());
        dto.setObjetivo_aprendizaje(bitacora.getObjetivo_aprendizaje());
        dto.setId_asignatura(bitacora.getAsignatura().getId_asignatura());
        return dto;
    }

    // ── Mapeo DTO → Entity ────────────────────────────────────────────────────
    private BitacoraAsignatura convertirAEntidad(BitacoraAsignaturaDTO dto) {
        BitacoraAsignatura bitacora = new BitacoraAsignatura();
        bitacora.setId_bitacora_asignatura(dto.getId_bitacora_asignatura());
        bitacora.setFecha_clase(dto.getFecha_clase());
        bitacora.setActividades_realizadas(dto.getActividades_realizadas());
        bitacora.setObservaciones_generales(dto.getObservaciones_generales());
        bitacora.setObjetivo_aprendizaje(dto.getObjetivo_aprendizaje());

        // Resuelve la FK hacia Asignatura
        Asignatura asignatura = asignaturaRepository.findById(dto.getId_asignatura())
                .orElseThrow(() -> new RuntimeException(
                        "Asignatura no encontrada con id: " + dto.getId_asignatura()));
        bitacora.setAsignatura(asignatura);

        return bitacora;
    }

}
