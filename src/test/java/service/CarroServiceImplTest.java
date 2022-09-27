package service;

import builder.CarroBuilder;
import builder.CarroProvider;
import model.Carro;
import org.hamcrest.CoreMatchers;
import org.junit.*;
import org.junit.rules.ExpectedException;

import java.security.Provider;

import static org.hamcrest.CoreMatchers.*;

public class CarroServiceImplTest {
    // F.I.R.S.T
    // F - Fast
    // I - Isolado/Independente
    // R - Repetable
    // S - Self-validating
    // T - Oportuno (TDD)

    // D.R.Y - Don't Repeat Yourself

    private CarroService carroService;

    @Before
    public void setup() {
        carroService = new CarroServiceImpl();
    }

    @Test
    @Ignore
    public void testeDeveAcelerarCorretamente() throws Exception {
        System.out.println("testeDeveAcelerarCorretamente");
        // Given
        Carro carro1 = CarroProvider.get();

        // When
        carroService.acelerar(carro1, 20);

        // Then
        Assert.assertEquals(20, carro1.getVelocidadeAtual());
    }

    @Test
    public void testeDeveFrearCorretamente() throws Exception {
        System.out.println("testeDeveFrearCorretamente");
        // Given
        Carro carro1 = CarroProvider.get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 20);
        carroService.frear(carro1, 10);

        // Then
        Assert.assertEquals(10, carro1.getVelocidadeAtual());
    }

    @Test
    public void testeVelocidadeNaoPodeSerNegativa() throws Exception {
        System.out.println("testeVelocidadeNaoPodeSerNegativa");

        // Given
        Carro carro1 = CarroBuilder.retonarCarro().get();

        // When
        carroService.acelerar(carro1, 20);
        carroService.frear(carro1, 20);
        carroService.frear(carro1, 20);

        // Then
        Assert.assertEquals(carro1.getVelocidadeAtual(), 0);
    }

    @Test
    public void testeNaoDeveAcelerarDesligado() throws Exception {
        System.out.println("testeNaoDeveAcelerarDesligado");

        // Given
        Carro carro1 = CarroBuilder.retonarCarro().get();


        // When
        carroService.acelerar(carro1, 20);

        // Then
        Assert.assertThat(carro1.isLigado(), is(false));
        Assert.assertThat(carro1.getVelocidadeAtual(), is(0));
    }

    @Test
    public void deveDesligarOCarroCorretamente() {
        System.out.println("deveDesligarOCarroCorretamente");

        // Given
        Carro carro1 = CarroBuilder.retonarCarro().get();

        // When
        carroService.ligar(carro1);
        carroService.desligar(carro1);

        // Then
        Assert.assertThat(carro1.isLigado(), is(false));
        Assert.assertThat(carro1.getVelocidadeAtual(), is(0));
    }

    @Test
    public void deveRetornarOEstadoAtualCorretamente() throws Exception {
        System.out.println("deveRetornarOEstadoAtualCorretamente");

        // Given
        Carro carro1 = CarroBuilder.retonarCarro().get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 100);

        // Then
        Assert.assertEquals("O carro está ligado: true e a velocidade atual é: 100", carroService.estadoAtual(carro1));
    }

    @Test
    public void deveLancarUmaExceptionQuandoAceleraValorNegativo() {
        Carro carro = CarroProvider.get();

        try {
            carroService.acelerar(carro, 0);
            Assert.fail("Não lancou a exception");
        } catch (Exception e) {
            Assert.assertThat(e.getMessage(), is("A velocidade deve ser maior que zero"));
        }
    }

    @Test(expected = Exception.class)
    public void deveLancarUmaExceptionQuandoAceleraValorNegativo_2() throws Exception {
        Carro carro = CarroProvider.get();

        carroService.acelerar(carro, -10);
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void deveLancarUmaExceptionQuandoAceleraValorNegativo_3() throws Exception {
        Carro carro = CarroProvider.get();

        // expect
        expectedException.expect(Exception.class);
        expectedException.expectMessage("A velocidade deve ser maior que zero");

        carroService.acelerar(carro, 0);
    }


}
