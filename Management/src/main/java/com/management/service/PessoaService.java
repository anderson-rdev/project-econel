package com.management.service;

import com.management.DTOs.*;
import com.management.exception.ResourceNotFoundException;
import com.management.model.*;
import com.management.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Serviço responsável por gerenciar operações de Pessoa.
 */
@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // =======================================
    // Cadastrar (POST)
    // =======================================
    public PessoaResponse cadastrar(PessoaRequest request) {
        Pessoa pessoa = converterRequestParaEntidade(request);

        // Garante a referência bidirecional Pessoa ↔ Endereço
        if (pessoa.getEnderecos() != null) {
            pessoa.getEnderecos().forEach(e -> e.setPessoa(pessoa));
        }

        Pessoa salva = pessoaRepository.save(pessoa);
        return converterParaResponse(salva);
    }

    // =======================================
    // Consultar (GET)
    // =======================================
    public PessoaResponse buscarPorId(Long id) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada"));
        return converterParaResponse(pessoa);
    }

    // =======================================
    // Alterar (PUT)
    // =======================================
    public PessoaResponse alterar(Long id, PessoaRequest request) {
        Pessoa pessoa = pessoaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada"));

        if (request.getNome() != null) pessoa.setNome(request.getNome());
        if (request.getTipoSanguineo() != null) pessoa.setTipoSanguineo(request.getTipoSanguineo());
        if (request.getContato() != null) pessoa.setContato(converterContato(request.getContato()));

        if (request.getEnderecos() != null) {
            List<Endereco> novosEnderecos = converterEnderecos(request.getEnderecos());
            novosEnderecos.forEach(e -> e.setPessoa(pessoa)); // vínculo
            pessoa.getEnderecos().clear();
            pessoa.getEnderecos().addAll(novosEnderecos);
        }

        Pessoa atualizada = pessoaRepository.save(pessoa);
        return converterParaResponse(atualizada);
    }

    // =======================================
    // Excluir (DELETE)
    // =======================================
    public void excluir(Long id) {
        if (!pessoaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Pessoa com ID " + id + " não encontrada");
        }
        pessoaRepository.deleteById(id);
    }

    // =====================================================
    // Conversores auxiliares (DTO ↔ Entidade)
    // =====================================================

    /**
     * Converte o DTO PessoaRequest em uma entidade Pessoa pronta para persistir.
     */
    private Pessoa converterRequestParaEntidade(PessoaRequest request) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(request.getNome());
        pessoa.setTipoSanguineo(request.getTipoSanguineo());

        if (request.getContato() != null) {
            pessoa.setContato(converterContato(request.getContato()));
        }

        if (request.getEnderecos() != null) {
            List<Endereco> enderecos = converterEnderecos(request.getEnderecos());
            pessoa.setEnderecos(enderecos);
        } else {
            pessoa.setEnderecos(new ArrayList<>());
        }

        return pessoa;
    }

    private Contato converterContato(ContatoDTO dto) {
        if (dto == null) return null;
        Contato contato = new Contato();
        contato.setTipo(dto.getTipo());
        contato.setValor(dto.getValor());
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
