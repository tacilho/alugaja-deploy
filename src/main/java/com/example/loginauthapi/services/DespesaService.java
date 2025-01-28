package com.example.loginauthapi.services;

import com.example.loginauthapi.dto.DespesaDTO;
import com.example.loginauthapi.entities.Despesa;
import com.example.loginauthapi.repositories.DespesaRepository;
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
public class DespesaService {

    @Autowired
    private DespesaRepository despesaRepository;

    public Despesa novaDespesa(Despesa despesa) {
        return despesaRepository.save(despesa);
    }

    public List<DespesaDTO> findAllDto() {
        List<Despesa> despesas = despesaRepository.findAll();
        return despesas.stream()
                .map(despesa -> new DespesaDTO(despesa))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        try {
            despesaRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Despesa findById(Long id) {
        Optional<Despesa> despesa = despesaRepository.findById(id);
        return despesa.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void updateData(Despesa despesa, Despesa obj) {
        despesa.setDescricao(obj.getDescricao());
        despesa.setCategoria(obj.getCategoria());
        despesa.setValor(obj.getValor());
        despesa.setData(obj.getData());
        despesa.setFormaPagamento(obj.getFormaPagamento());
        despesa.setFornecedor(obj.getFornecedor());
        despesa.setMoto(obj.getMoto());
        despesa.setCentroCusto(obj.getCentroCusto());
        despesa.setComprovante(obj.getComprovante());
        despesa.setObservacoes(obj.getObservacoes());
    }

    @Transactional
    public Despesa update(Long id, Despesa obj) {
        try {
            Despesa despesa = despesaRepository.getReferenceById(id);
            updateData(despesa, obj);
            return despesaRepository.save(despesa);
        } catch(EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }
}

