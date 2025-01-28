package com.example.loginauthapi.repositories;

import com.example.loginauthapi.entities.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v LEFT JOIN FETCH v.manutencoes WHERE v.id = :id")
    Optional<Veiculo> buscarVeiculoComManutencoesPorId(@Param("id") Long id);
}
