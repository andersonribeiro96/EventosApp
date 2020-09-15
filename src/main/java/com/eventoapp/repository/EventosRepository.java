package com.eventoapp.repository;

import com.eventoapp.models.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventosRepository extends JpaRepository<Evento, Long> {
    Evento findByCodigo(Long condigo);
}
