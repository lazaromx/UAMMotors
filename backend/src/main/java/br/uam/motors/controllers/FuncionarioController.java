package br.uam.motors.controllers;

import br.uam.motors.models.Funcionario;
import br.uam.motors.models.FuncionarioDTO;
import br.uam.motors.repositories.FuncionarioRepository;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

@RestController
@RequestMapping("funcionarios")
public class FuncionarioController {
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @GetMapping()
    public ResponseEntity<Iterable <Funcionario>> getFuncionarios(){
        Iterable<Funcionario> funcionarios = funcionarioRepository.findAll();
        return ResponseEntity.ok(funcionarios);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody FuncionarioDTO dto){
        Funcionario funcionario = funcionarioRepository.findByUsuarioAndSenha(dto.getUsuario(), dto.getSenha());
        if(funcionario == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario ou senha incorretos");
        }
        return ResponseEntity.ok().body(funcionario);
    }
}
