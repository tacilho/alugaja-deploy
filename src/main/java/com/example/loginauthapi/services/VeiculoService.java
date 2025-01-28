package com.example.loginauthapi.services;

import com.example.loginauthapi.dto.VeiculoDTO;
import com.example.loginauthapi.entities.Manutencao;
import com.example.loginauthapi.entities.Veiculo;
import com.example.loginauthapi.repositories.ManutencaoRepository;
import com.example.loginauthapi.repositories.VeiculoRepository;
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
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private ManutencaoRepository manutencaoRepository;  // Certifique-se de ter um repositório para Manutencao

    @Transactional
    public Veiculo novoVeiculo(Veiculo veiculo) {
        List<Long> manutencaoIds = veiculo.getManutencoes().stream()
                .map(Manutencao::getId)
                .collect(Collectors.toList());

        // Carrega todas as manutenções de uma vez
        List<Manutencao> manutencaoExistenteList = manutencaoRepository.findAllById(manutencaoIds);

        // Garante que apenas as manutenções existentes serão associadas
        veiculo.setManutencoes(manutencaoExistenteList);

        return veiculoRepository.save(veiculo);
    }

    public List<VeiculoDTO> findAllDto() {
        List<Veiculo> veiculos = veiculoRepository.findAll();
        return veiculos.stream()
                .map(veiculo -> new VeiculoDTO(veiculo))
                .collect(Collectors.toList());
    }

    public Veiculo findById(Long id) {
        Optional<Veiculo> veiculo = veiculoRepository.buscarVeiculoComManutencoesPorId(id);
        return veiculo.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public void updateData(Veiculo veiculo, Veiculo obj) {
        veiculo.setApelidoMoto(obj.getApelidoMoto());
        veiculo.setPlaca(obj.getPlaca());
        veiculo.setRenavam(obj.getRenavam());
        veiculo.setMarca(obj.getMarca());
        veiculo.setModelo(obj.getModelo());
        veiculo.setAnoFabricacao(obj.getAnoFabricacao());
        veiculo.setCor(obj.getCor());
        veiculo.setKmInicial(obj.getKmInicial());
        veiculo.setKmAtual(obj.getKmAtual());
        veiculo.setKmRodado(obj.getKmRodado());
        veiculo.setValorCompra(obj.getValorCompra());
        veiculo.setDataVenda(obj.getDataVenda());
        veiculo.setValorVenda(obj.getValorVenda());
        veiculo.setParcelaVeiculo(obj.getParcelaVeiculo());
        veiculo.setVencimentoIpva(obj.getVencimentoIpva());
        veiculo.setStatusIpva(obj.getStatusIpva());
        veiculo.setVencimentoLicenciamento(obj.getVencimentoLicenciamento());
        veiculo.setStatusLicenciamento(obj.getStatusLicenciamento());
        veiculo.setSeguro(obj.getSeguro());
        veiculo.setVencimentoSeguro(obj.getVencimentoSeguro());
        veiculo.setValorSeguro(obj.getValorSeguro());
        veiculo.setStatus(obj.getStatus());
        veiculo.setManutencoes(obj.getManutencoes());
        veiculo.setDocumentoVeiculo(obj.getDocumentoVeiculo());
        veiculo.setObservacoes(obj.getObservacoes());
    }

    @Transactional
    public Veiculo update(Long id, Veiculo obj) {
        try {
            Veiculo veiculo = veiculoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException(id));

            // Resolva entidades detached
            if (obj.getManutencoes() != null) {
                obj.setManutencoes(obj.getManutencoes().stream()
                        .map(manutencao -> manutencaoRepository.findById(manutencao.getId())
                                .orElseThrow(() -> new RuntimeException("Manutenção não encontrada: " + manutencao.getId())))
                        .collect(Collectors.toList()));
            }

            updateData(veiculo, obj);
            return veiculoRepository.save(veiculo);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void delete(Long id) {
        try {
            veiculoRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }
}

