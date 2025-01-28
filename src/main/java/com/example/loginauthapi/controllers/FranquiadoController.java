package com.example.loginauthapi.controllers;

import com.example.loginauthapi.entities.Franquiado;
import com.example.loginauthapi.services.FranquiadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/franquiados")
public class FranquiadoController {

    @Autowired
    private FranquiadoService service;

    @PostMapping
    public ResponseEntity<Franquiado> insert(@RequestBody Franquiado obj){
        obj = service.novoFranquiado(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }
}

