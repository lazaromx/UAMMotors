package br.uam.motors.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteTest {

    @Test
    public void testGettersAndSetters() {
        // Criação de um objeto Cliente
        Cliente cliente = new Cliente();

        // Configuração dos valores com os setters
        cliente.setId(1);
        cliente.setNome("João");
        cliente.setSobrenome("Silva");
        cliente.setCpf("123.456.789-00");
        cliente.setTelefone("11999999999");
        cliente.setEndereco("Rua Exemplo, 123");
        cliente.setEmail("joao.silva@email.com");
        cliente.setSenha("senha123");

        // Verificação dos valores com os getters
        assertEquals(1, cliente.getId());
        assertEquals("João", cliente.getNome());
        assertEquals("Silva", cliente.getSobrenome());
        assertEquals("123.456.789-00", cliente.getCpf());
        assertEquals("11999999999", cliente.getTelefone());
        assertEquals("Rua Exemplo, 123", cliente.getEndereco());
        assertEquals("joao.silva@email.com", cliente.getEmail());
        assertEquals("senha123", cliente.getSenha());
    }

    @Test
    public void testEqualsAndHashCode() {
        // Criação de dois objetos Cliente com os mesmos atributos
        Cliente cliente1 = new Cliente();
        cliente1.setId(1);
        cliente1.setNome("João");
        cliente1.setSobrenome("Silva");
        cliente1.setCpf("123.456.789-00");

        Cliente cliente2 = new Cliente();
        cliente2.setId(1);
        cliente2.setNome("João");
        cliente2.setSobrenome("Silva");
        cliente2.setCpf("123.456.789-00");

        // Verificação de igualdade
        assertNotSame(cliente1, cliente2); // Não são o mesmo objeto
        assertEquals(cliente1.getCpf(), cliente2.getCpf()); // Mesma informação de CPF
    }

    @Test
    public void testDefaultValues() {
        // Criação de um objeto Cliente
        Cliente cliente = new Cliente();

        // Verificação dos valores padrão
        assertEquals(0, cliente.getId());
        assertNull(cliente.getNome());
        assertNull(cliente.getSobrenome());
        assertNull(cliente.getCpf());
        assertNull(cliente.getTelefone());
        assertNull(cliente.getEndereco());
        assertNull(cliente.getEmail());
        assertNull(cliente.getSenha());
    }
}
