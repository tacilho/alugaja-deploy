package com.example.loginauthapi.controllers;

import com.example.loginauthapi.dto.VeiculoDTO;
import com.example.loginauthapi.entities.Veiculo;
import com.example.loginauthapi.services.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoService service;

    @PostMapping
    public ResponseEntity<Veiculo> insert(@RequestBody Veiculo obj) {
        obj = service.novoVeiculo(obj);  // Salva o veículo, garantindo que as manutenções estejam corretamente associadas
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @GetMapping
    public ResponseEntity<List<VeiculoDTO>> findAllDto() {
        List<VeiculoDTO> list = service.findAllDto();
        return ResponseEntity.ok().body(list);
    }

    //Lista veiculos por Id com manutenções associadas
    @GetMapping(value = "/{id}")
    public ResponseEntity<Veiculo> findById(@PathVariable Long id) {
        Veiculo veiculo = service.findById(id);
        return ResponseEntity.ok().body(veiculo);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Veiculo> update(@PathVariable Long id, @RequestBody Veiculo veiculo){
        veiculo = service.update(id, veiculo);
        return ResponseEntity.ok().body(veiculo);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

