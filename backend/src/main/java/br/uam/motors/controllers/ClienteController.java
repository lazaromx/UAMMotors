package br.uam.motors.controllers;

import br.uam.motors.models.Cliente;
import br.uam.motors.models.ClienteDTO;
import br.uam.motors.models.LoginClienteDTO;
import br.uam.motors.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("clientes")

public class ClienteController {
    @Autowired
    public ClienteRepository clienteRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Cliente>> getClientes() {
        Iterable<Cliente> clientes = clienteRepository.findAll();
        return ResponseEntity.ok(clientes);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar (@Valid @RequestBody ClienteDTO dto, BindingResult result){
        if(clienteRepository.findByEmail(dto.getEmail()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
        }
        if(clienteRepository.findByCpf(dto.getCpf()) != null){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cpf já cadastrado");
        }
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setSobrenome(dto.getSobrenome());
        cliente.setCpf(dto.getCpf());
        cliente.setTelefone(dto.getTelefone());
        cliente.setEndereco(dto.getEndereco());
        cliente.setEmail(dto.getEmail());
        cliente.setSenha(dto.getSenha());

        clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", cliente.getId()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login (@Valid @RequestBody LoginClienteDTO dto) {
        Cliente cliente = clienteRepository.findByEmailAndSenha(dto.getEmail(), dto.getSenha());

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Email ou senha incorretos");
        }
        return ResponseEntity.ok().body(cliente);
    }

}    
