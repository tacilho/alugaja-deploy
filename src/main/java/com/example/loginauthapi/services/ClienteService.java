package com.example.loginauthapi.services;

import com.example.loginauthapi.dto.ClienteDTO;
import com.example.loginauthapi.entities.Cliente;
import com.example.loginauthapi.repositories.ClienteRepository;
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
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente novoCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<ClienteDTO> findAllDto(){
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(cliente -> new ClienteDTO(cliente))
                .collect(Collectors.toList());
    }

    public void delete(Long id) {
        try {
            clienteRepository.deleteById(id);
        } catch(EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch(DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Cliente findById(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    @Transactional
    public Cliente update(Long id, Cliente obj) {
        try {
            Cliente cliente = clienteRepository.getReferenceById(id);
            updateData(cliente, obj);
            return clienteRepository.save(cliente);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    public void updateData(Cliente cliente, Cliente obj) {
        cliente.setNomeCompleto(obj.getNomeCompleto());
        cliente.setDataNascimento(obj.getDataNascimento());
        cliente.setCpf(obj.getCpf());
        cliente.setRg(obj.getRg());
        cliente.setCnh(obj.getCnh());
        cliente.setTipoCnh(obj.getTipoCnh());
        cliente.setVencimentoCnh(obj.getVencimentoCnh());
        cliente.setStatusCnh(obj.getStatusCnh());
        cliente.setTelefone(obj.getTelefone());
        cliente.setCelular1(obj.getCelular1());
        cliente.setCelular2(obj.getCelular2());

        cliente.setBairro(obj.getBairro());
        cliente.setCep(obj.getCep());
        cliente.setCidade(obj.getCidade());
        cliente.setEstado(obj.getEstado());
        cliente.setFoto(obj.getFoto());
        cliente.setIdContrato(obj.getIdContrato());
        cliente.setPlanoEscolhido(obj.getPlanoEscolhido());
        cliente.setRua(obj.getRua());

        // Adicionados os campos que estavam faltando:
        cliente.setNumeroCasa(obj.getNumeroCasa());
        cliente.setStatus(obj.getStatus());
        cliente.setEmail(obj.getEmail());
    }

}