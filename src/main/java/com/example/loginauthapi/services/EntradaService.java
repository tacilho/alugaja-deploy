package com.example.loginauthapi.services;

import com.example.loginauthapi.dto.EntradaDTO;
import com.example.loginauthapi.entities.Entrada;
import com.example.loginauthapi.repositories.EntradaRepository;
import com.example.loginauthapi.services.exceptions.DatabaseException;
import com.example.loginauthapi.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    public Entrada novaEntrada(Entrada entrada) {
        return entradaRepository.save(entrada);
    }

    public List<EntradaDTO> findAllDto(){
        List<Entrada> entradas = entradaRepository.findAll();
        return entradas.stream()
                .map(entrada -> new EntradaDTO(entrada))
                .collect(Collectors.toList());
    }

    public Entrada findById(Long id) {
        Optional<Entrada> entrada = entradaRepository.findById(id);
        return entrada.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void updateData(Entrada entrada, Entrada obj) {
        entrada.setTipoEntrada(obj.getTipoEntrada());
        entrada.setValor(obj.getValor());
        entrada.setDataRegistro(obj.getDataRegistro());
        entrada.setFormaPagamento(obj.getFormaPagamento());
        entrada.setStatus(obj.getStatus());
        entrada.setComprovante(obj.getComprovante());
        entrada.setObservacoes(obj.getObservacoes());
    }

    @Transactional
    public Entrada update(Long id, Entrada obj) {
        try {
            Entrada entrada = entradaRepository.getReferenceById(id);
            updateData(entrada, obj);
            return entradaRepository.save(entrada);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            entradaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}

