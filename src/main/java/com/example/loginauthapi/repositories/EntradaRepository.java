package com.example.loginauthapi.repositories;

import com.example.loginauthapi.entities.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntradaRepository extends JpaRepository<Entrada, Long> {
}
