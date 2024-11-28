package br.uam.motors.repositories;

import br.uam.motors.models.Funcionario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
    public Funcionario findByUsuarioAndSenha(String usuario, String senha);
}
