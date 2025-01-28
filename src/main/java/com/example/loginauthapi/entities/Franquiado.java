package com.example.loginauthapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_franquiados")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Franquiado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private String cnh;
    private String tipoCnh;
    private String cep;
    private String rua;
    private String bairro;
    private String cidade;
    private String estado;
    private String planoEscolhido;
    private String idContrato;
    private String foto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Franquiado that = (Franquiado) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
