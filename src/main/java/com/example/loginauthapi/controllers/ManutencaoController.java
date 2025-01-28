package com.example.loginauthapi.controllers;

import com.example.loginauthapi.dto.ManutencaoDTO;
import com.example.loginauthapi.entities.Manutencao;
import com.example.loginauthapi.services.ManutencaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/manutencoes")
public class ManutencaoController {

    @Autowired
    private ManutencaoService service;

    @PostMapping
    public ResponseEntity<Manutencao> insert(@RequestBody Manutencao obj){
        obj = service.novaManutencao(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<ManutencaoDTO>> findAll(){
        List<ManutencaoDTO> list = service.findAllDto();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Manutencao> findById(@PathVariable Long id){
        Manutencao manutencao = service.findById(id);
        return ResponseEntity.ok().body(manutencao);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Manutencao> update(@PathVariable Long id, @RequestBody Manutencao manutencao){
        manutencao = service.update(id, manutencao);
        return ResponseEntity.ok().body(manutencao);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

