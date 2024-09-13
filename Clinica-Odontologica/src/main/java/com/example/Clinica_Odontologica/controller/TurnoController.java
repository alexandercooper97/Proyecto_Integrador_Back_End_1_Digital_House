package com.example.Clinica_Odontologica.controller;

import com.example.Clinica_Odontologica.entity.Turno;
import com.example.Clinica_Odontologica.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping
    public List<Turno> listarTurnos() {
        return turnoService.listarTurnos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turno> buscarTurno(@PathVariable Long id) {
        return turnoService.buscarTurnoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Turno> crearTurno(@RequestBody Turno turno) {
        return ResponseEntity.ok(turnoService.guardarTurno(turno));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Turno> actualizarTurno(@PathVariable Long id, @RequestBody Turno turno) {
        return turnoService.buscarTurnoPorId(id)
                .map(turnoExistente -> {
                    turno.setId(id);
                    return ResponseEntity.ok(turnoService.guardarTurno(turno));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> eliminarTurno(@PathVariable Long id) {
        if (turnoService.buscarTurnoPorId(id).isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}