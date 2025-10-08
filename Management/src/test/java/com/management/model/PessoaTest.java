package com.management.model;

import com.management.enums.Contatos;
import com.management.enums.TipoSanguineo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {

    @Test
    public void TesteCadastroPessoa() {

        // Instancia Pessoa
        Pessoa pessoa = new Pessoa(1L, "Anderson Ramos");

        // Instancia do tipo sanguíneo
        TipoSanguineo tipoSanguineo = TipoSanguineo.O_POSITIVO;

        // Instancia do tipo contato
        Contatos contatos = Contatos.CELULAR;

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
        pessoa.setContato(contatos);

        // Verificação
        assertEquals(1, pessoa.getEnderecos().size());

        // Saída: apenas o nome
        System.out.println(pessoa);
    }


}
