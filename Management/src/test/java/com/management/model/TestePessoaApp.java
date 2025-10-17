package com.management.model;

import com.management.enums.TipoSanguineo;
import com.management.enums.Contatos;
import com.management.model.Contato;
import com.management.model.Endereco;
import com.management.model.Pessoa;
import com.management.service.PessoaService;

public class TestePessoaApp {
    public static void main(String[] args) {
        PessoaService service = new PessoaService();

        // Instancia Pessoa
        Pessoa pessoa = new Pessoa(2l, "Anderson Nascimento");

        // Instancia do tipo sanguíneo
        TipoSanguineo tipoSanguineo = TipoSanguineo.O_POSITIVO;

        // Instancia do contato (supondo enum Contatos e construtor Contato(tipo, valor))
        Contato contato = new Contato(Contatos.CELULAR, "(11) 93930-2000");

        // Criando endereço
        Endereco endereco = new Endereco(
                "Rua das Flores",
                "123",
                "Centro",
                "São Paulo",
                "SP",
                "01000-000",
                "Residencial"
        );

        // Adicionando endereço
        pessoa.addEndereco(endereco);

        // Setando tipo sanguíneo
        pessoa.setTipoSanguineo(tipoSanguineo);

        // Setando contato
        pessoa.setContato(contato);

        // 1) Cadastrar
        Pessoa salvo = service.cadastrar(pessoa);
        System.out.println("Pessoa cadastrada (ID atribuído): " + salvo.getId());

        // 2) Listar
        System.out.println("\n=== Lista ===");
        service.listar().forEach(p -> System.out.println(p.imprimirResumo()));

        // 3) Consultar por ID
        // System.out.println("\n=== Consulta ID ===");
        // Pessoa consultada = service.consultar(salvo.getId());
        // System.out.println(consultada.imprimirResumo());

        // 4) Alterar (ex: trocar contato)
        // consultada.setContato(new Contato(Contatos.EMAIL, "derson.email@teste.com"));
        // service.alterar(consultada.getId(), consultada);
        // System.out.println("\n=== Após alteração ===");
        // System.out.println(service.consultar(consultada.getId()).imprimirResumo());

        // 5) Excluir
        // service.excluir(consultada.getId());
        // System.out.println("\n=== Após exclusão ===");
        // service.listar().forEach(p -> System.out.println(p.imprimirResumo()));
    }
}
