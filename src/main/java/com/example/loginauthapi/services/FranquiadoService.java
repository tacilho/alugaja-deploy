package com.example.loginauthapi.services;

import com.example.loginauthapi.entities.Franquiado;
import com.example.loginauthapi.repositories.FranquiadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FranquiadoService {

    @Autowired
    private FranquiadoRepository franquiadoRepository;

    public Franquiado novoFranquiado(Franquiado franquiado) {
        return franquiadoRepository.save(franquiado);
    }
}
