// OdontologoService.java
package com.example.Clinica_Odontologica.service;



import com.example.Clinica_Odontologica.entity.Odontologo;
import com.example.Clinica_Odontologica.repository.OdontologoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OdontologoService {

    @Autowired
    private OdontologoRepository odontologoRepository;

    public List<Odontologo> listarOdontologos() {
        return odontologoRepository.findAll();
    }

    public Optional<Odontologo> buscarOdontologoPorId(Long id) {
        return odontologoRepository.findById(id);
    }

    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoRepository.save(odontologo);
    }

    public void eliminarOdontologo(Long id) {
        odontologoRepository.deleteById(id);
    }
}