// OdontologoController.java
package com.example.Clinica_Odontologica.controller;

import com.example.Clinica_Odontologica.entity.Odontologo;
import com.example.Clinica_Odontologica.service.OdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {

    @Autowired
    private OdontologoService odontologoService;

    @GetMapping
    public List<Odontologo> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Odontologo> buscarOdontologo(@PathVariable Long id) {
        return odontologoService.buscarOdontologoPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Odontologo> crearOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Odontologo> actualizarOdontologo(@PathVariable Long id, @RequestBody Odontologo odontologo) {
        return odontologoService.buscarOdontologoPorId(id)
                .map(odontologoExistente -> {
                    odontologo.setId(id);
                    return ResponseEntity.ok(odontologoService.guardarOdontologo(odontologo));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> eliminarOdontologo(@PathVariable Long id) {
        if (odontologoService.buscarOdontologoPorId(id).isPresent()) {
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}