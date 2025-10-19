package com.management.service;

import com.management.DTOs.*;
import com.management.enums.TipoEndereco;
import com.management.exception.ResourceNotFoundException;
import com.management.model.*;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Serviço responsável por gerenciar operações de Pessoa.
 */
@Service
public class PessoaService {

    // Simula um repositório em memória (modo de teste)
    private final Map<Long, Pessoa> pessoas = new HashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // =======================================
    // Criar (POST)
    // =======================================
    public PessoaResponse cadastrar(PessoaRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setId(idGenerator.getAndIncrement());
        pessoa.setNome(request.getNome());
        pessoa.setTipoSanguineo(request.getTipoSanguineo());
        pessoa.setContato(converterContato(request.getContato()));
        pessoa.setEnderecos(converterEnderecos(request.getEnderecos()));

        pessoas.put(pessoa.getId(), pessoa);
        return converterParaResponse(pessoa);
        // System.out.println("Request Endereços: " + request.getEnderecos());
    }

    // =======================================
    // Consultar (GET) || Id
    // =======================================
    public PessoaResponse buscarPorId(Long id) {
        Pessoa pessoa = pessoas.get(id);
        if (pessoa == null) {
            throw new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada");
        }
        return converterParaResponse(pessoa);
    }

    // =======================================
    // Alterar (PUT)
    // =======================================
    public PessoaResponse alterar(Long id, PessoaRequest request) {
        Pessoa pessoa = pessoas.get(id);
        if (pessoa == null) {
            throw new ResourceNotFoundException("Pessoa com Id " + id + "não encontrado(a). ");
        }

        if (request.getNome() != null) pessoa.setNome(request.getNome());
        if (request.getTipoSanguineo() != null) pessoa.setTipoSanguineo(request.getTipoSanguineo());
        if (request.getContato() != null) pessoa.setContato(converterContato(request.getContato()));
        if (request.getEnderecos() != null && !request.getEnderecos().isEmpty()) {
            pessoa.setEnderecos(converterEnderecos(request.getEnderecos()));
        }

        pessoas.put(id, pessoa);
        return converterParaResponse(pessoa);
    }

    // =====================================================
    // Conversores auxiliares (DTO ↔ Entidade)
    // =====================================================

    private Contato converterContato(ContatoDTO dto) {
        if (dto == null) return null;
        Contato contato = new Contato(dto.getTipo(), dto.getValor());
        return contato;
    }

    private List<Endereco> converterEnderecos(List<EnderecoDTO> dtos) {
        if (dtos == null) return new ArrayList<>();
        return dtos.stream().map(dto -> {
            Endereco e = new Endereco();
            e.setRua(dto.getRua());
            e.setNumero(dto.getNumero());
            e.setBairro(dto.getBairro());
            e.setCidade(dto.getCidade());
            e.setEstado(dto.getEstado());
            e.setCep(dto.getCep());
            e.setTipo(dto.getTipo());
            return e;
        }).collect(Collectors.toList());
    }

    private EnderecoDTO converterEnderecoParaDTO(Endereco endereco) {
        if (endereco == null) return null;

        EnderecoDTO dto = new EnderecoDTO();
        dto.setRua(endereco.getRua());
        dto.setNumero(endereco.getNumero());
        dto.setBairro(endereco.getBairro());
        dto.setCidade(endereco.getCidade());
        dto.setEstado(endereco.getEstado());
        dto.setCep(endereco.getCep());
        dto.setTipo(endereco.getTipo());
        return dto;
    }

    private PessoaResponse converterParaResponse(Pessoa pessoa) {
        if (pessoa == null) return null;

        PessoaResponse response = new PessoaResponse();
        response.setId(pessoa.getId());
        response.setNome(pessoa.getNome());
        response.setTipoSanguineo(pessoa.getTipoSanguineo());

        // Contato
        if (pessoa.getContato() != null) {
            ContatoDTO contatoDTO = new ContatoDTO();
            contatoDTO.setTipo(pessoa.getContato().getTipo());
            contatoDTO.setValor(pessoa.getContato().getValor());
            response.setContato(contatoDTO);
        }

        // Endereços
        List<EnderecoDTO> enderecosDTO = new ArrayList<>();
        if (pessoa.getEnderecos() != null) {
            for (Endereco e : pessoa.getEnderecos()) {
                enderecosDTO.add(converterEnderecoParaDTO(e));
            }
        }
        response.setEnderecos(enderecosDTO);

        return response;
    }

}
