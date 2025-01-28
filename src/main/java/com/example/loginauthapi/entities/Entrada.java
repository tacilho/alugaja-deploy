package com.example.loginauthapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_entradas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tipoEntrada;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal valor;

    private Date dataRegistro;
    private String formaPagamento;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String comprovante;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entrada entrada = (Entrada) o;
        return Objects.equals(id, entrada.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
