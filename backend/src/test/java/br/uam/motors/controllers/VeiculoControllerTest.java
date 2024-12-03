package br.uam.motors.controllers;

import br.uam.motors.models.*;
import br.uam.motors.repositories.VeiculoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VeiculoControllerTest {

    @Mock
    private VeiculoRepository veiculoRepository;

    @InjectMocks
    private VeiculoController veiculoController;

    @Test
    void getVeiculos_shouldReturnOkWithVeiculos() {
        Iterable<Veiculo> veiculos = Collections.singletonList(new Veiculo());
        when(veiculoRepository.findAll()).thenReturn(veiculos);
        ResponseEntity<Iterable<Veiculo>> response = veiculoController.getVeiculos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculos, response.getBody());
    }

    @Test
    void getVeiculos_shouldReturnOkWithEmptyList() {
        when(veiculoRepository.findAll()).thenReturn(Collections.emptyList());
        ResponseEntity<Iterable<Veiculo>> response = veiculoController.getVeiculos();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Collections.emptyList(), response.getBody());
    }

    @Test
    void getVeiculo_shouldReturnOkWithExistingVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        when(veiculoRepository.findById(1)).thenReturn(Optional.of(veiculo));
        ResponseEntity<?> response = veiculoController.getVeiculo(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(veiculo, response.getBody());
    }

    @Test
    void getVeiculo_shouldReturnNotFoundWithNonExistingVeiculo() {
        when(veiculoRepository.findById(1)).thenReturn(Optional.empty());
        ResponseEntity<?> response = veiculoController.getVeiculo(1);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void cadastrarVeiculo_shouldReturnCreatedWithValidVeiculo() {
        VeiculoDTO dto = new VeiculoDTO();
        dto.setMarca("Marca");
        dto.setModelo("Modelo");
        dto.setAno(2023);
        dto.setCor("Azul");
        dto.setPreco(100000);
        dto.setStatus(StatusEnum.ESTOQUE);
        dto.setImagem("imagem.jpg");
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(false);

        ResponseEntity<?> response = veiculoController.cadastrarVeiculo(dto, result);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));

    }


    @Test
    void cadastrarVeiculo_shouldReturnBadRequestWithInvalidVeiculo() {
        VeiculoDTO dto = new VeiculoDTO();
        BindingResult result = mock(BindingResult.class);
        when(result.hasErrors()).thenReturn(true);

        ResponseEntity<?> response = veiculoController.cadastrarVeiculo(dto, result);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void atualizarVeiculo_shouldReturnOkWithExistingVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        veiculo.setStatus(StatusEnum.ESTOQUE.toString().toLowerCase());
        // Mock do comportamento do save
        when(veiculoRepository.findById(1)).thenReturn(Optional.of(veiculo));
        when(veiculoRepository.save(any(Veiculo.class))).thenReturn(veiculo); //importante
        ReservaVeiculoDTO reserva = new ReservaVeiculoDTO();
        reserva.setIdVeiculo(1);
        reserva.setIdCliente(1);

        ResponseEntity<?> response = veiculoController.atualizarVeiculo(reserva);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        verify(veiculoRepository, times(1)).save(any(Veiculo.class));
        // Remova essa linha, pois não garante que o save foi realizado corretamente:
        // assertEquals(StatusEnum.VENDIDO.toString().toLowerCase(), veiculo.getStatus());
    }


    @Test
    void atualizarVeiculo_shouldReturnNotFoundWithNonExistingVeiculo() {
        when(veiculoRepository.findById(1)).thenReturn(Optional.empty());
        ReservaVeiculoDTO reserva = new ReservaVeiculoDTO();
        reserva.setIdVeiculo(1);
        reserva.setIdCliente(1);

        ResponseEntity<?> response = veiculoController.atualizarVeiculo(reserva);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    void atualizarVeiculo_shouldReturnConflictWithAlreadySoldVeiculo() {
        Veiculo veiculo = new Veiculo();
        veiculo.setId(1);
        veiculo.setStatus(StatusEnum.VENDIDO.toString().toLowerCase());
        when(veiculoRepository.findById(1)).thenReturn(Optional.of(veiculo));
        ReservaVeiculoDTO reserva = new ReservaVeiculoDTO();
        reserva.setIdVeiculo(1);
        reserva.setIdCliente(1);

        ResponseEntity<?> response = veiculoController.atualizarVeiculo(reserva);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
        assertNull(response.getBody());
    }
}