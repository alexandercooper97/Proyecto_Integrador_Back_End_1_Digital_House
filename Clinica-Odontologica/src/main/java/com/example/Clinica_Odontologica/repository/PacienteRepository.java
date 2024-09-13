// PacienteRepository.java

package com.example.Clinica_Odontologica.repository;

import com.example.Clinica_Odontologica.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
}