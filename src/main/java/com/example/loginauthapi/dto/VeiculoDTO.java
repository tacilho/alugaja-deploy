package com.example.loginauthapi.dto;

import com.example.loginauthapi.entities.Veiculo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class VeiculoDTO {

    private Long id;
    private String modelo;
    private String cor;
    private String placa;

    public VeiculoDTO(Veiculo entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
