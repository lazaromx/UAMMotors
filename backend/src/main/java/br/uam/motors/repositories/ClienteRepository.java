package br.uam.motors.repositories;

import br.uam.motors.models.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {
    public Cliente findByEmail(String email);
    public Cliente findByCpf(String cpf);

    public Cliente findByEmailAndSenha(String email, String senha);
}
