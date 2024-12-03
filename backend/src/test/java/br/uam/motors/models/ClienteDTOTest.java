package br.uam.motors.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testValidClienteDTO() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Mateus");
        clienteDTO.setSobrenome("Kalil");
        clienteDTO.setCpf("12345678900");
        clienteDTO.setTelefone("11987654321");
        clienteDTO.setEndereco("Rua Exemplo, 123");
        clienteDTO.setEmail("mateus.kalil@example.com");
        clienteDTO.setSenha("senha123");


        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);


        assertTrue(violations.isEmpty(), "Não deve haver erros para ClienteDTO válido");
    }

    @Test
    void testInvalidEmail() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Mateus");
        clienteDTO.setSobrenome("Kalil");
        clienteDTO.setCpf("12345678900");
        clienteDTO.setTelefone("11987654321");
        clienteDTO.setEndereco("Rua Exemplo, 123");
        clienteDTO.setEmail("email-invalido");
        clienteDTO.setSenha("senha123");


        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);


        assertFalse(violations.isEmpty());
        assertEquals(1, violations.size());
        ConstraintViolation<ClienteDTO> violation = violations.iterator().next();
        assertEquals("Email é obrigatório", violation.getMessage());
    }

    @Test
    void testMissingRequiredFields() {

        ClienteDTO clienteDTO = new ClienteDTO();


        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);


        assertFalse(violations.isEmpty());
        assertEquals(5, violations.size());
    }

    @Test
    void testOptionalFields() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Mateus");
        clienteDTO.setSobrenome("Kalil");
        clienteDTO.setCpf("12345678900");
        clienteDTO.setEmail("mateus.kalil@example.com");
        clienteDTO.setSenha("senha123");


        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);


        assertTrue(violations.isEmpty());
    }

    @Test
    void testInvalidCPF() {

        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNome("Mateus");
        clienteDTO.setSobrenome("Kalil");
        clienteDTO.setCpf(""); // CPF vazio
        clienteDTO.setEmail("mateus.kalil@example.com");
        clienteDTO.setSenha("senha123");


        Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);


        assertFalse(violations.isEmpty());
        ConstraintViolation<ClienteDTO> violation = violations.iterator().next();
        assertEquals("cpf é obrigatório", violation.getMessage());
    }
}
