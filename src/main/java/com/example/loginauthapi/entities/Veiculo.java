package com.example.loginauthapi.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_veiculos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String apelidoMoto;
    private String placa;
    private String renavam;
    private String marca;
    private String modelo;
    private Integer anoFabricacao;
    private String cor;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal kmInicial;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal kmAtual;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal kmRodado;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal valorCompra;

    private Date dataVenda;
    private BigDecimal valorVenda;
    private Integer parcelaVeiculo;
    private Date vencimentoIpva;
    private String statusIpva;
    private Date vencimentoLicenciamento;
    private String statusLicenciamento;
    private String seguro;
    private Date vencimentoSeguro;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal valorSeguro;

    private String status;

    @Cascade(CascadeType.ALL)
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "veiculo_manutencao",
            joinColumns = @JoinColumn(name = "veiculo_id"),
            inverseJoinColumns = @JoinColumn(name = "manutencao_id")
    )
    private List<Manutencao> manutencoes = new ArrayList<>();

    @Column(columnDefinition = "TEXT")
    private String documentoVeiculo;

    @Column(columnDefinition = "TEXT")
    private String observacoes;
}
