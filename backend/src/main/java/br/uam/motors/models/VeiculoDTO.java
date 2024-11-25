package br.uam.motors.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VeiculoDTO {

    @NotEmpty(message = "Marca é obrigatória")
    private String marca;

    @NotEmpty(message = "Modelo é obrigatório")
    private String modelo;

    //    @Max(value = 2024, message = "Ano não pode ser maior que o ano atual")
    @Positive(message = "Ano deve ser maior que zero")
    @NotNull(message = "Ano é obrigatório")
    private int ano;

    @NotEmpty(message = "Cor é obrigatória")
    private String cor;

    @Positive(message = "Preço deve ser maior que zero")
    @NotNull(message = "Preço é obrigatório")
    private float preco;

    @NotEmpty(message = "Status é obrigatório")
    private String status;

    private String imagem;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public float getPreco() {
        return preco;
    }

    public void setPreco(float preco) {
        this.preco = preco;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getImagem(){
        return imagem;
    }

    public void setImagem(String imagem){
        this.imagem = imagem;
    }
}
