package service;

import model.Carro;
import org.junit.Assert;
import org.junit.Test;

public class CarroServiceTest {
    @Test
    public void testeVelocidadeNaoPodeSerNegativa() {
        CarroService carroService = new CarroServiceImpl();

        // Given
        Carro carro1 =
                new Carro("prata", "GM", "2012", "Celta");

        // When
        carroService.acelerar(carro1, 20);
        carroService.frear(carro1, 20);
        carroService.frear(carro1, 20);

        // Then
        Assert.assertTrue(carro1.getVelocidadeAtual() == 0);
    }

    @Test
    public void testeDeveAcelerarCorretamente() {
        CarroService carroService = new CarroServiceImpl();

        // Given
        Carro carro1 =
                new Carro("prata", "GM", "2012", "Celta");


        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 20);

        // Then
        Assert.assertTrue(carro1.getVelocidadeAtual() == 20);
    }

    @Test
    public void testeNaoDeveAcelerarDesligado() {
        CarroService carroService = new CarroServiceImpl();

        // Given
        Carro carro1 =
                new Carro("prata", "GM", "2012", "Celta");


        // When
        carroService.acelerar(carro1, 20);

        // Then
        Assert.assertTrue(carro1.getVelocidadeAtual() == 0);
    }
}
