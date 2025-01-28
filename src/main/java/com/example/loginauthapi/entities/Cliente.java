package com.example.loginauthapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCompleto;
    private Date dataNascimento;
    private String cpf;
    private String rg;
    private String cnh;
    private String tipoCnh;
    private Date vencimentoCnh;
    private String statusCnh;
    private String telefone;
    private String celular1;
    private String celular2;
    private String email;
    private String cep;
    private String rua;
    private String numeroCasa;
    private String bairro;
    private String cidade;
    private String estado;
    private String planoEscolhido;
    private String idContrato;
    private String status;

    @Column(columnDefinition = "TEXT")
    private String foto;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nomeCompleto, cliente.nomeCompleto) && Objects.equals(dataNascimento, cliente.dataNascimento) && Objects.equals(cpf, cliente.cpf) && Objects.equals(rg, cliente.rg) && Objects.equals(cnh, cliente.cnh) && Objects.equals(tipoCnh, cliente.tipoCnh) && Objects.equals(telefone, cliente.telefone) && Objects.equals(celular1, cliente.celular1) && Objects.equals(celular2, cliente.celular2) && Objects.equals(email, cliente.email) && Objects.equals(cep, cliente.cep) && Objects.equals(rua, cliente.rua) && Objects.equals(numeroCasa, cliente.numeroCasa) && Objects.equals(bairro, cliente.bairro) && Objects.equals(cidade, cliente.cidade) && Objects.equals(estado, cliente.estado) && Objects.equals(planoEscolhido, cliente.planoEscolhido) && Objects.equals(idContrato, cliente.idContrato) && Objects.equals(status, cliente.status) && Objects.equals(foto, cliente.foto);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
