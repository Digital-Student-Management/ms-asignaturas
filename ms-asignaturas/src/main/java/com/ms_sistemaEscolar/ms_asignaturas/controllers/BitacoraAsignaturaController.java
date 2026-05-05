package com.ms_sistemaEscolar.ms_asignaturas.controllers;

import com.ms_sistemaEscolar.ms_asignaturas.models.dto.BitacoraAsignaturaDTO;
import com.ms_sistemaEscolar.ms_asignaturas.services.BitacoraAsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bitacoras")
public class BitacoraAsignaturaController {

    @Autowired
    private BitacoraAsignaturaService bitacoraAsignaturaService;

    // ── GET /api/bitacoras ────────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<BitacoraAsignaturaDTO>> listar() {
        List<BitacoraAsignaturaDTO> bitacoras = bitacoraAsignaturaService.listar();
        return ResponseEntity.ok(bitacoras);
    }

    // ── GET /api/bitacoras/{id} ───────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<BitacoraAsignaturaDTO> buscarPorId(@PathVariable int id) {
        BitacoraAsignaturaDTO bitacora = bitacoraAsignaturaService.buscarPorId(id);
        if (bitacora == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(bitacora);
    }

    // ── GET /api/bitacoras/asignatura/{idAsignatura} ──────────────────────────
    @GetMapping("/asignatura/{idAsignatura}")
    public ResponseEntity<List<BitacoraAsignaturaDTO>> listarPorAsignatura(
            @PathVariable int idAsignatura) {
        List<BitacoraAsignaturaDTO> bitacoras =
                bitacoraAsignaturaService.listarPorAsignatura(idAsignatura);
        return ResponseEntity.ok(bitacoras);
    }

    // ── POST /api/bitacoras ───────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<BitacoraAsignaturaDTO> guardar(@RequestBody BitacoraAsignaturaDTO dto) {
        BitacoraAsignaturaDTO guardada = bitacoraAsignaturaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    // ── PUT /api/bitacoras/{id} ───────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<BitacoraAsignaturaDTO> actualizar(@PathVariable int id,
                                                             @RequestBody BitacoraAsignaturaDTO dto) {
        dto.setId_bitacora_asignatura(id);
        BitacoraAsignaturaDTO actualizada = bitacoraAsignaturaService.guardar(dto);
        return ResponseEntity.ok(actualizada);
    }

    // ── DELETE /api/bitacoras/{id} ────────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        bitacoraAsignaturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
