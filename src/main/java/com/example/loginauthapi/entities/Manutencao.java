package com.example.loginauthapi.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_manutencoes")
@AllArgsConstructor
@NoArgsConstructor
public class Manutencao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;
    private Integer intervaloKm;
    private Integer intervaloMeses;
    private Date dataManutencao;

    @JsonIgnore
    @ManyToMany(mappedBy = "manutencoes")
    private List<Veiculo> veiculos = new ArrayList<>();

    private Integer kmTroca;
    private Integer kmProximaTroca;
    private String statusTroca;
    private String pecaSubstituida;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal valorMaoDeObra;

    @Column(precision = 10, scale = 2) //até 10 dígitos, com 2 casas decimais
    private BigDecimal valorTotal;

    @Column(columnDefinition = "TEXT")
    private String observacoes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getIntervaloKm() {
        return intervaloKm;
    }

    public void setIntervaloKm(Integer intervaloKm) {
        this.intervaloKm = intervaloKm;
    }

    public Integer getIntervaloMeses() {
        return intervaloMeses;
    }

    public void setIntervaloMeses(Integer intervaloMeses) {
        this.intervaloMeses = intervaloMeses;
    }

    public Date getDataManutencao() {
        return dataManutencao;
    }

    public void setDataManutencao(Date dataManutencao) {
        this.dataManutencao = dataManutencao;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public void setVeiculos(List<Veiculo> veiculos) {
        this.veiculos = veiculos;
    }

    public Integer getKmTroca() {
        return kmTroca;
    }

    public void setKmTroca(Integer kmTroca) {
        this.kmTroca = kmTroca;
    }

    public Integer getKmProximaTroca() {
        return kmProximaTroca;
    }

    public void setKmProximaTroca(Integer kmProximaTroca) {
        this.kmProximaTroca = kmProximaTroca;
    }

    public String getStatusTroca() {
        return statusTroca;
    }

    public void setStatusTroca(String statusTroca) {
        this.statusTroca = statusTroca;
    }

    public String getPecaSubstituida() {
        return pecaSubstituida;
    }

    public void setPecaSubstituida(String pecaSubstituida) {
        this.pecaSubstituida = pecaSubstituida;
    }

    public BigDecimal getValorMaoDeObra() {
        return valorMaoDeObra;
    }

    public void setValorMaoDeObra(BigDecimal valorMaoDeObra) {
        this.valorMaoDeObra = valorMaoDeObra;
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(BigDecimal valorTotal) {
        this.valorTotal = valorTotal;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }
}
