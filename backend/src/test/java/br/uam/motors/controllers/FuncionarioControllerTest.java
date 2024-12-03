package br.uam.motors.controllers;

import br.uam.motors.models.Funcionario;
import br.uam.motors.models.FuncionarioDTO;
import br.uam.motors.repositories.FuncionarioRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FuncionarioControllerTest {

    @Mock
    private FuncionarioRepository funcionarioRepository;

    @InjectMocks
    private FuncionarioController funcionarioController;

    @Test
    void getFuncionarios_shouldReturnOkWithFuncionarios() {
        List<Funcionario> funcionarios = List.of(new Funcionario());
        when(funcionarioRepository.findAll()).thenReturn(funcionarios);
        ResponseEntity<Iterable<Funcionario>> response = funcionarioController.getFuncionarios();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionarios, response.getBody());
    }

    @Test
    void getFuncionarios_shouldReturnOkWithEmptyList() {
        when(funcionarioRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<Iterable<Funcionario>> response = funcionarioController.getFuncionarios();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void login_shouldReturnOkWithValidCredentials() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setUsuario("usuario");
        dto.setSenha("senha");
        Funcionario funcionario = new Funcionario();
        when(funcionarioRepository.findByUsuarioAndSenha(dto.getUsuario(), dto.getSenha())).thenReturn(funcionario);
        ResponseEntity<?> response = funcionarioController.login(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(funcionario, response.getBody());
    }

    @Test
    void login_shouldReturnNotFoundWithInvalidCredentials() {
        FuncionarioDTO dto = new FuncionarioDTO();
        dto.setUsuario("usuarioInvalido");
        dto.setSenha("senhaInvalida");
        when(funcionarioRepository.findByUsuarioAndSenha(dto.getUsuario(), dto.getSenha())).thenReturn(null);
        ResponseEntity<?> response = funcionarioController.login(dto);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Usuario ou senha incorretos", response.getBody());
    }
}