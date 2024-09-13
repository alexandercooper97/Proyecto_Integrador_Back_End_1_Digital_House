// PacienteController.java
package com.example.Clinica_Odontologica.controller;

import com.example.Clinica_Odontologica.entity.Paciente;
import com.example.Clinica_Odontologica.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping
    public List<Paciente> listarPacientes() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> buscarPaciente(@PathVariable Long id) {
        return pacienteService.buscarPacientePorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Paciente> crearPaciente(@RequestBody Paciente paciente) {
        return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Paciente> actualizarPaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
        return pacienteService.buscarPacientePorId(id)
                .map(pacienteExistente -> {
                    paciente.setId(id);
                    return ResponseEntity.ok(pacienteService.guardarPaciente(paciente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {
        if (pacienteService.buscarPacientePorId(id).isPresent()) {
            pacienteService.eliminarPaciente(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}