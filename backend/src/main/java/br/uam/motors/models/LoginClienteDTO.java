package br.uam.motors.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class LoginClienteDTO {

    @NotEmpty(message = "Email é obrigatório")
    @Email
    private String email;

    @NotEmpty(message = "Senha é obrigatório")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}