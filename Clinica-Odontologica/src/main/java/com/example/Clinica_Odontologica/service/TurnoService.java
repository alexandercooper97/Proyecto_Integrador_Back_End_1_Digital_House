// TurnoService.java
package com.example.Clinica_Odontologica.service;

import com.example.Clinica_Odontologica.entity.Turno;
import com.example.Clinica_Odontologica.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoRepository turnoRepository;

    public List<Turno> listarTurnos() {
        return turnoRepository.findAll();
    }

    public Optional<Turno> buscarTurnoPorId(Long id) {
        return turnoRepository.findById(id);
    }

    public Turno guardarTurno(Turno turno) {
        return turnoRepository.save(turno);
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }
}