package com.example.loginauthapi.dto;

import com.example.loginauthapi.entities.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.BeanUtils;

@Getter
@Setter
@NoArgsConstructor
public class ClienteDTO {

    private Long id;
    private String nomeCompleto;
    private String celular1;
    private String idContrato;

    public ClienteDTO(Cliente entity) {
        BeanUtils.copyProperties(entity, this);
    }
}
