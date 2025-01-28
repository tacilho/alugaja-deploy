package com.example.loginauthapi.dto;

import com.example.loginauthapi.entities.Entrada;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class EntradaDTO {

    private Long id;
    private String descricao;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;
    private Date vencimento;
    private String status;

    public EntradaDTO(Entrada entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
