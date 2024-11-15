package br.uam.motors.controllers;

import br.uam.motors.models.Cliente;
import br.uam.motors.models.ClienteDTO;
import br.uam.motors.repositories.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("")
//@CrossOrigin(origins="http://localhost:4200")
public class ClienteController {
@Autowired
public ClienteRepository clienteRepository;

@GetMapping("clientes")
public ResponseEntity<Iterable<Cliente>> getClientes() {
    Iterable<Cliente> clientes = clienteRepository.findAll();
    return ResponseEntity.ok(clientes);
}

@GetMapping("clientes/{id}")
public ResponseEntity<Iterable<Cliente>> getCliente() {
    Iterable<Cliente> clientes = clienteRepository.findAll();
    return ResponseEntity.ok(clientes);
}

@PostMapping("clientes")
public ResponseEntity<?> cadastrar (@Valid @RequestBody ClienteDTO dto, BindingResult result){
    if(clienteRepository.findByEmail(dto.getEmail()) != null){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
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
    return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
}

//    public ResponseEntity<?> cadastrarCliente(@Valid @RequestBody ClienteDTO dto, BindingResult result){
//        if(clienteRepository.findByEmail(dto.getEmail()) !=null) {
//            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado");
//        }
//
//        if(result.hasErrors()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
//        }
//
//        Cliente cliente = new Cliente();
//        cliente.setNome(dto.getNome());
//        cliente.setSobrenome(dto.getSobrenome());
//        cliente.setCpf(dto.getCpf());
//        cliente.setTelefone(dto.getTelefone());
//        cliente.setEndereco(dto.getEndereco());
//        cliente.setEmail(dto.getEmail());
//        cliente.setSenha(dto.getSenha());
//
//        clienteRepository.save(cliente);
//
//        return ResponseEntity.status(HttpStatus.CREATED).body(cliente);
//    }

//    @GetMapping({"", "/"})
//    //Entrar no bd, buscar todos os elementos e trazer uma lista
//    public String getClientes(Model model){
////
////        //Varredura de todos os clientes do db
////        var clientes = clienteRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
////
////        //Atribuindo a clientes do front infomações da variavel cliente do back
////        model.addAttribute("clientes", clientes);
////
////        //Pasta clientes e arquivo index.html
//        return "clientes/index";
//    }
}
