package br.uam.motors.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class FuncionarioTest {
    @Test
    public void testFuncionario_GettersAndSetters() {
        Funcionario funcionario = new Funcionario();

        // Definir valores
        funcionario.setId(1);
        funcionario.setNome("Carlos");
        funcionario.setSobrenome("Souza");
        funcionario.setTelefone("987654321");
        funcionario.setSenha("senha123");
        funcionario.setUsuario("carlos.souza");

        // Verificar se os valores são atribuídos corretamente
        assertEquals(1, funcionario.getId());
        assertEquals("Carlos", funcionario.getNome());
        assertEquals("Souza", funcionario.getSobrenome());
        assertEquals("987654321", funcionario.getTelefone());
        assertEquals("senha123", funcionario.getSenha());
        assertEquals("carlos.souza", funcionario.getUsuario());
    }

    @Test
    public void testFuncionario_CriarFuncionario() {
        // Criando um objeto Funcionario com valores válidos
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("Ana");
        funcionario.setSobrenome("Pereira");
        funcionario.setTelefone("987654321");
        funcionario.setSenha("senha456");
        funcionario.setUsuario("ana.pereira");

        // Verificar se os valores atribuídos estão corretos
        assertNotNull(funcionario.getNome());
        assertNotNull(funcionario.getSobrenome());
        assertNotNull(funcionario.getSenha());
        assertNotNull(funcionario.getUsuario());

        assertEquals("Ana", funcionario.getNome());
        assertEquals("Pereira", funcionario.getSobrenome());
        assertEquals("senha456", funcionario.getSenha());
        assertEquals("ana.pereira", funcionario.getUsuario());
    }

}
