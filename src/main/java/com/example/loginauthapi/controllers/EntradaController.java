package com.example.loginauthapi.controllers;

import com.example.loginauthapi.dto.EntradaDTO;
import com.example.loginauthapi.entities.Entrada;
import com.example.loginauthapi.services.EntradaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/entradas")
public class EntradaController {

    @Autowired
    private EntradaService service;

    @PostMapping
    public ResponseEntity<Entrada> insert(@RequestBody Entrada obj){
        obj = service.novaEntrada(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<EntradaDTO>> findAll(){
        List<EntradaDTO> list = service.findAllDto();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Entrada> findById(@PathVariable Long id){
        Entrada entrada = service.findById(id);
        return ResponseEntity.ok().body(entrada);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Entrada> update(@PathVariable Long id, @RequestBody Entrada entrada)	{
        entrada = service.update(id, entrada);
        return ResponseEntity.ok().body(entrada);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

