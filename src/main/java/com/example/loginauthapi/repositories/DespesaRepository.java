package com.example.loginauthapi.repositories;

import com.example.loginauthapi.entities.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DespesaRepository extends JpaRepository<Despesa, Long> {
}
