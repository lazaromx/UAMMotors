package br.uam.motors.repositories;

import br.uam.motors.models.Veiculo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VeiculoRepository extends CrudRepository<Veiculo, Integer> {
    Optional<Veiculo> findById(int id);
}
