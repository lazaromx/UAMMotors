package br.uam.motors.controllers;

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
    public VeiculoRepository veicRepository;

    @GetMapping()
    public ResponseEntity<Iterable<Veiculo>> getClientes() {
        Iterable<Veiculo> veics = veicRepository.findAll();
        return ResponseEntity.ok(veics);
    }

    @PostMapping()
    public ResponseEntity<?> cadastrar (@Valid @RequestBody VeiculoDTO dto, BindingResult result){
       
        if(result.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }

        Veiculo veic = new Veiculo();

        veic.setMarca(dto.getMarca());
        veic.setModelo(dto.getModelo());
        veic.setAno(dto.getAno());
        veic.setCor(dto.getCor());
        veic.setPreco(dto.getPreco());
        veic.setStatus(dto.getStatus());
        veic.setImagem(dto.getImagem());

        veicRepository.save(veic);
        return ResponseEntity.status(HttpStatus.CREATED).body(veic);
    }

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
