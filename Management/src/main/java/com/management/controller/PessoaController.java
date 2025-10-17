package com.management.controller;

import com.management.model.Pessoa;
import com.management.service.PessoaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
public class PessoaController {

    private final PessoaService service = new PessoaService();

    @PostMapping
    public Pessoa cadastrar(@RequestBody Pessoa pessoa) {
        return service.cadastrar(pessoa);
    }

    @PutMapping("/{id}")
    public Pessoa alterar(@PathVariable Long id, @RequestBody Pessoa pessoa) {
        return service.alterar(id, pessoa);
    }

    @GetMapping("/{id}")
    public Pessoa consultar(@PathVariable Long id) {
        return service.consultar(id);
    }

    @GetMapping
    public List<Pessoa> listar() {
        return service.listar();
    }

    @DeleteMapping("/{id}")
    public String excluir(@PathVariable Long id) {
        boolean removido = service.excluir(id);
        return removido ? "Pessoa removida com sucesso." : "Pessoa não encontrada.";
    }
}
