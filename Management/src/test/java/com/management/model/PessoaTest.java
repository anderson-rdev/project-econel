package com.management.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PessoaTest {

    @Test
    void testAdicionarEndereco() {
        Pessoa p = new Pessoa();
        Endereco e1 = new Endereco("Rua das Flores", "123", "Centro", "São Paulo", "SP", "01000-000", "Residencial");
        p.addEndereco(e1);

        assertEquals(1, p.getEnderecos().size());

        System.out.println("Registro:" +  p.getId());
        System.out.println("Nome:" +  p.getNome());
        System.out.println(e1);

    }
}
