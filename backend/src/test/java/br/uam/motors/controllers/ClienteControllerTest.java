package br.uam.motors.controllers;

import br.uam.motors.models.Cliente;
import br.uam.motors.models.ClienteDTO;
import br.uam.motors.models.LoginClienteDTO;
import br.uam.motors.repositories.ClienteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ClienteControllerTest {

    @Mock
    private ClienteRepository clienteRepository;

    @InjectMocks
    private ClienteController clienteController;

    @Test
    void getClientes_shouldReturnOkWithClientes() {
        List<Cliente> clientes = List.of(new Cliente());
        when(clienteRepository.findAll()).thenReturn(clientes);
        ResponseEntity<Iterable<Cliente>> response = clienteController.getClientes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(clientes, response.getBody());
    }

    @Test
    void getClientes_shouldReturnOkWithEmptyList() {
        when(clienteRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<Iterable<Cliente>> response = clienteController.getClientes();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }


    @Test
    void cadastrar_shouldReturnConflictIfEmailExists() {
        ClienteDTO dto = new ClienteDTO();
        dto.setEmail("teste@email.com");
        when(clienteRepository.findByEmail(dto.getEmail())).thenReturn(new Cliente());
        ResponseEntity<?> response = clienteController.cadastrar(dto,null);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Email já cadastrado", response.getBody());
    }

    @Test
    void cadastrar_shouldReturnConflictIfCpfExists() {
        ClienteDTO dto = new ClienteDTO();
        dto.setCpf("12345678900");
        when(clienteRepository.findByCpf(dto.getCpf())).thenReturn(new Cliente());
        ResponseEntity<?> response = clienteController.cadastrar(dto,null);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertEquals("Cpf já cadastrado", response.getBody());
    }

    @Test
    void cadastrar_shouldReturnCreatedWithNewCliente() {
        ClienteDTO dto = new ClienteDTO();
        dto.setNome("Teste");
        dto.setSobrenome("Teste");
        dto.setCpf("99999999999");
        dto.setEmail("teste2@email.com");
        dto.setSenha("senha");
        when(clienteRepository.findByEmail(dto.getEmail())).thenReturn(null);
        when(clienteRepository.findByCpf(dto.getCpf())).thenReturn(null);

        // Criando um mock para BindingResult
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false); // Simulando validação bem-sucedida

        ResponseEntity<?> response = clienteController.cadastrar(dto, result);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertTrue(((Map<?, ?>) response.getBody()).containsKey("id"));

        verify(clienteRepository, times(1)).save(any(Cliente.class));
    }

    @Test
    void login_shouldReturnOkWithValidCredentials() {
        LoginClienteDTO dto = new LoginClienteDTO();
        dto.setEmail("teste@email.com");
        dto.setSenha("senha");
        Cliente cliente = new Cliente();
        when(clienteRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha())).thenReturn(cliente);
        ResponseEntity<?> response = clienteController.login(dto);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cliente, response.getBody());
    }

    @Test
    void login_shouldReturnUnauthorizedWithInvalidCredentials() {
        LoginClienteDTO dto = new LoginClienteDTO();
        dto.setEmail("teste@email.com");
        dto.setSenha("senhaErrada");
        when(clienteRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha())).thenReturn(null);
        ResponseEntity<?> response = clienteController.login(dto);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Email ou senha incorretos", response.getBody());
    }
}
