// TurnoRepository.java
package com.example.Clinica_Odontologica.repository;

import com.example.Clinica_Odontologica.entity.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoRepository extends JpaRepository<Turno, Long> {
}