package br.uam.motors.controllers;

import br.uam.motors.models.Cliente;
import br.uam.motors.models.Veiculo;
import br.uam.motors.models.VeiculoDTO;
import br.uam.motors.repositories.VeiculoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("veiculos")
//@CrossOrigin(origins="http://localhost:4200")
public class VeiculoController {
    @Autowired
    public VeiculoRepository veiculoRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Veiculo>> getVeiculos() {
        Iterable<Veiculo> veiculos = veiculoRepository.findAll();
        return ResponseEntity.ok(veiculos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getVeiculo(@PathVariable int id) {
        Veiculo veiculo = veiculoRepository.findById(id);
        return ResponseEntity.ok(veiculo);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarVeiculo (@Valid @RequestBody VeiculoDTO dto, BindingResult result){

        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        Veiculo veiculo = new Veiculo();

        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setAno(dto.getAno());
        veiculo.setCor(dto.getCor());
        veiculo.setPreco(dto.getPreco());
        veiculo.setStatus(dto.getStatus().toString().toLowerCase());
        veiculo.setImagem(dto.getImagem());

        veiculoRepository.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }

}
