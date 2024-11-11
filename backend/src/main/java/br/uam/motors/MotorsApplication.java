package br.uam.motors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MotorsApplication {
	public static void main(String[] args) {
		SpringApplication.run(MotorsApplication.class, args);
	}

	@GetMapping("/clientes")
	public Customer clientes() {
		Customer cliente = new Customer();
		cliente.nome = "Lazaro";
		cliente.sobrenome = "Marques";
		return cliente;
	}
}

class Customer {
	public String nome;
	public String sobrenome;

	@Override
	public String toString() {
		return "Customer [nome=" + nome + ", sobrenome=" + sobrenome + "]";
	}
}