package br.uam.motors.models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class VeiculoTest {

    @Test
    public void testVeiculo_GettersAndSetters() {
        Veiculo veiculo = new Veiculo();

        // Definindo valores
        veiculo.setMarca("Toyota");
        veiculo.setModelo("Corolla");
        veiculo.setAno(2024);
        veiculo.setCor("Preto");
        veiculo.setPreco(120000.50f);
        veiculo.setStatus("estoque");
        veiculo.setImagem("corolla2024.jpg");

        // Verificando se os valores estão sendo atribuídos corretamente
        assertEquals("Toyota", veiculo.getMarca());
        assertEquals("Corolla", veiculo.getModelo());
        assertEquals(2024, veiculo.getAno());
        assertEquals("Preto", veiculo.getCor());
        assertEquals(120000.50f, veiculo.getPreco());
        assertEquals("estoque", veiculo.getStatus());
        assertEquals("corolla2024.jpg", veiculo.getImagem());
    }

    @Test
    public void testVeiculo_DefaultStatus() {
        Veiculo veiculo = new Veiculo();

        // Verificando o valor padrão do campo 'status'
        assertEquals("estoque", veiculo.getStatus());
    }

    @Test
    public void testVeiculo_DefaultValues() {
        Veiculo veiculo = new Veiculo();

        // Verificando os valores padrão ao criar o objeto
        assertEquals(0, veiculo.getId());  // ID será 0, pois não foi setado
        assertNull(veiculo.getMarca());    // Marca não foi setada
        assertNull(veiculo.getModelo());   // Modelo não foi setado
        assertEquals(0, veiculo.getAno()); // Ano não foi setado
        assertNull(veiculo.getCor());      // Cor não foi setada
        assertEquals(0.0f, veiculo.getPreco());  // Preço não foi setado
        assertEquals("estoque", veiculo.getStatus());  // Status deve ser o valor padrão
        assertNull(veiculo.getImagem());  // Imagem não foi setada
    }


//    @Test
//    public void testGettersAndSetters() {
//        // Criação de um objeto Veiculo
//        Veiculo veiculo = new Veiculo();
//
//        // Configuração dos valores com os setters
//        veiculo.setId(1);
//        veiculo.setMarca("Toyota");
//        veiculo.setModelo("Corolla");
//        veiculo.setAno(2023);
//        veiculo.setCor("Prata");
//        veiculo.setPreco(120000.50f);
//        veiculo.setStatus(StatusEnum.ESTOQUE.toString().toLowerCase());
//
//        // Verificação dos valores com os getters
//        assertEquals(1, veiculo.getId());
//        assertEquals("Toyota", veiculo.getMarca());
//        assertEquals("Corolla", veiculo.getModelo());
//        assertEquals(2023, veiculo.getAno());
//        assertEquals("Prata", veiculo.getCor());
//        assertEquals(120000.50f, veiculo.getPreco(), 0.01); // Tolerância para float
//        assertEquals("Disponível", veiculo.getStatus());
//    }
//
//    @Test
//    public void testDefaultValues() {
//        // Criação de um objeto Veiculo
//        Veiculo veiculo = new Veiculo();
//
//        // Verificação dos valores padrão (objetos recém-criados devem ter valores padrão)
//        assertEquals(0, veiculo.getId());
//        assertNull(veiculo.getMarca());
//        assertNull(veiculo.getModelo());
//        assertEquals(0, veiculo.getAno());
//        assertNull(veiculo.getCor());
//        assertEquals(0.0f, veiculo.getPreco(), 0.01);
//        assertNull(veiculo.getStatus());
//    }
//
//    @Test
//    public void testInvalidYear() {
//        // Teste para validar anos fora de um intervalo lógico
//        Veiculo veiculo = new Veiculo();
//        veiculo.setAno(1800); // Ano inválido hipotético
//        assertTrue(veiculo.getAno() < 1900 || veiculo.getAno() > 2100, "O ano não deveria ser válido: " + veiculo.getAno());
//    }
}