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
@Table(name = "tb_despesas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Despesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private String categoria;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal valor;

    private Date data;
    private String formaPagamento;
    private String fornecedor;
    private String moto;
    private String centroCusto;

    @Column(columnDefinition = "TEXT")
    private String comprovante;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Despesa despesa = (Despesa) o;
        return Objects.equals(id, despesa.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
