package com.ms_sistemaEscolar.ms_asignaturas.controllers;

import com.ms_sistemaEscolar.ms_asignaturas.models.dto.AsignaturaDTO;
import com.ms_sistemaEscolar.ms_asignaturas.services.AsignaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/asignaturas")
public class AsignaturaController {

    @Autowired
    private AsignaturaService asignaturaService;

    // ── GET /api/asignaturas ──────────────────────────────────────────────────
    @GetMapping
    public ResponseEntity<List<AsignaturaDTO>> listar() {
        List<AsignaturaDTO> asignaturas = asignaturaService.listar();
        return ResponseEntity.ok(asignaturas);
    }

    // ── GET /api/asignaturas/{id} ─────────────────────────────────────────────
    @GetMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> buscarPorId(@PathVariable int id) {
        AsignaturaDTO asignatura = asignaturaService.buscarPorId(id);
        if (asignatura == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(asignatura);
    }

    // ── POST /api/asignaturas ─────────────────────────────────────────────────
    @PostMapping
    public ResponseEntity<AsignaturaDTO> guardar(@RequestBody AsignaturaDTO dto) {
        AsignaturaDTO guardada = asignaturaService.guardar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
    }

    // ── PUT /api/asignaturas/{id} ─────────────────────────────────────────────
    @PutMapping("/{id}")
    public ResponseEntity<AsignaturaDTO> actualizar(@PathVariable int id,
                                                     @RequestBody AsignaturaDTO dto) {
        dto.setId_asignatura(id);
        AsignaturaDTO actualizada = asignaturaService.guardar(dto);
        return ResponseEntity.ok(actualizada);
    }

    // ── DELETE /api/asignaturas/{id} ──────────────────────────────────────────
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable int id) {
        asignaturaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
