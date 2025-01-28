package com.example.loginauthapi.dto;

import com.example.loginauthapi.entities.Franquiado;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class FranquiadoDTO {

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

    public FranquiadoDTO(Franquiado entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
