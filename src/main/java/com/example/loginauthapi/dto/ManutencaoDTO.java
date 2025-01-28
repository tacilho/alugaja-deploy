package com.example.loginauthapi.dto;

import com.example.loginauthapi.entities.Manutencao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class ManutencaoDTO {

    private Long id;
    private String descricao;
    private Integer intervaloKm;
    private Integer intervaloMeses;
    private String custoEstimado;

    public ManutencaoDTO(Manutencao entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
