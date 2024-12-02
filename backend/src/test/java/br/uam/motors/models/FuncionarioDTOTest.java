package br.uam.motors.models;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class FuncionarioDTOTest {
    @Test
    public void testFuncionarioDTO_GettersAndSetters() {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        // Definindo valores
        funcionarioDTO.setNome("Roberto");
        funcionarioDTO.setSobrenome("Lima");
        funcionarioDTO.setTelefone("998877665");
        funcionarioDTO.setSenha("senha123");
        funcionarioDTO.setUsuario("roberto.lima");

        // Verificando se os valores estão sendo atribuídos corretamente
        assertEquals("Roberto", funcionarioDTO.getNome());
        assertEquals("Lima", funcionarioDTO.getSobrenome());
        assertEquals("998877665", funcionarioDTO.getTelefone());
        assertEquals("senha123", funcionarioDTO.getSenha());
        assertEquals("roberto.lima", funcionarioDTO.getUsuario());
    }

    @Test
    public void testFuncionarioDTO_EmptyFields() {
        FuncionarioDTO funcionarioDTO = new FuncionarioDTO();

        // Verificando se os campos estão inicialmente nulos
        assertNull(funcionarioDTO.getNome());
        assertNull(funcionarioDTO.getSobrenome());
        assertNull(funcionarioDTO.getTelefone());
        assertNull(funcionarioDTO.getSenha());
        assertNull(funcionarioDTO.getUsuario());
    }

}
