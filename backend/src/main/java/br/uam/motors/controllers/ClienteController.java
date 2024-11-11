package br.uam.motors.controllers;

import br.uam.motors.models.Cliente;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {

    @GetMapping("/clientes")
    public Cliente clientes() {
        Cliente cliente = new Cliente();
        cliente.nome = "Lazaro";
        cliente.sobrenome = "Marques";
        return cliente;
    }


}
