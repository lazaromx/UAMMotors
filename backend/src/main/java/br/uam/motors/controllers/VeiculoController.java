package br.uam.motors.controllers;

import br.uam.motors.models.Veiculo;
import br.uam.motors.models.VeiculoDTO;
import br.uam.motors.repositories.VeiculoRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("veiculos")
public class VeiculoController {
    @Autowired
    VeiculoRepository veiculoRepository;

    @GetMapping("")
    public ResponseEntity<Iterable<Veiculo>> getVeiculos() {
        Iterable<Veiculo> veiculos = veiculoRepository.findAll();
        return ResponseEntity.ok(veiculos);
    }

    @PostMapping("")
    public ResponseEntity<?> cadastrarVeiculos(@Valid @RequestBody VeiculoDTO dto, BindingResult result){
        if(result.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        Veiculo veiculo = new Veiculo();
        veiculo.setMarca(dto.getMarca());
        veiculo.setModelo(dto.getModelo());
        veiculo.setAno(dto.getAno());
        veiculo.setCor(dto.getCor());
        veiculo.setPreco(dto.getPreco());
        veiculo.setStatus(dto.getStatus());

        veiculoRepository.save(veiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(veiculo);
    }
}
