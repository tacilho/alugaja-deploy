package com.example.loginauthapi.services;

import com.example.loginauthapi.dto.ManutencaoDTO;
import com.example.loginauthapi.entities.Manutencao;
import com.example.loginauthapi.repositories.ManutencaoRepository;
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
public class ManutencaoService {

    @Autowired
    private ManutencaoRepository manutencaoRepository;

    public Manutencao novaManutencao(Manutencao manutencao) {
        return manutencaoRepository.save(manutencao);
    }

    public List<ManutencaoDTO> findAllDto(){
        List<Manutencao> manutencoes = manutencaoRepository.findAll();
        return manutencoes.stream()
                .map(manutencao -> new ManutencaoDTO(manutencao))
                .collect(Collectors.toList());
    }

    public Manutencao findById(Long id) {
        Optional<Manutencao> manutencao = manutencaoRepository.findById(id);
        return manutencao.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void updateData(Manutencao manutencao, Manutencao obj) {
        manutencao.setDescricao(obj.getDescricao());
        manutencao.setIntervaloKm(obj.getIntervaloKm());
        manutencao.setIntervaloMeses(obj.getIntervaloMeses());
        manutencao.setDataManutencao(obj.getDataManutencao());
        manutencao.setKmTroca(obj.getKmTroca());
        manutencao.setKmProximaTroca(obj.getKmProximaTroca());
        manutencao.setStatusTroca(obj.getStatusTroca());
        manutencao.setPecaSubstituida(obj.getPecaSubstituida());
        manutencao.setValorMaoDeObra(obj.getValorMaoDeObra());
        manutencao.setValorTotal(obj.getValorTotal());
        manutencao.setObservacoes(obj.getObservacoes());
    }

    @Transactional
    public Manutencao update(Long id, Manutencao obj) {
        try {
            Manutencao manutencao = manutencaoRepository.getReferenceById(id);
            updateData(manutencao, obj);
            return manutencaoRepository.save(manutencao);
        } catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            manutencaoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}

