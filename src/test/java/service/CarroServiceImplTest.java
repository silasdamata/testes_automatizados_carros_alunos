package service;

import builder.CarroBuilder;
import builder.CarroProvider;
import model.Carro;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.rules.ExpectedException;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.jupiter.api.Assertions.*;

public class CarroServiceImplTest {
    // F.I.R.S.T
    // F - Fast
    // I - Isolado/Independente
    // R - Repetable
    // S - Self-validating
    // T - Oportuno (TDD)

    // D.R.Y - Don't Repeat Yourself

    private CarroService carroService;

    /**
     *
     * Annotations
     *
     * @Before // Junit 4 - Antes de todos os testes da classe
     *  ->
     * @BeforeEach // Junit 5
     *
     * 
     * @After // Junit 4 - Depois de todos os testes da classe
     * ->
     * @AfterEach // Junit 5
     *
     * 
     * @BeforeClass // Junit 4 - Antes da classe
     * ->
     * @BeforeAll // Junit 5
     *
     * 
     * @AfterClass // Depois da classe
     * ->
     * @AfterAll // Junit 5
     *
     * 
     * @Ignore // Junit 4
     * ->
     * @Disabled // Junit 5
     *
     * Novo:
     * @DisplayName
     *
     *
     * ---
     * Assertions
     *
     * Nome da classe: Assert -> Assertions
     *
     * fail - fail
     * assertTrue - assertTrue
     * assertSame - assertSame
     * assertNull - assertNull
     * assertNotSame - assertNotSame
     * assertNotNull - assertNotNull
     * assertFalse - assertFalse
     * assertEquals - assertEquals
     * assertArrayEquals - assertArrayEquals
     * assertThat - N/A
     *
     * Nova:
     * assertAll
     * assertThrows
     *
     */

    @BeforeEach
    public void setup() {
        carroService = new CarroServiceImpl();
    }

    @Test
    @Disabled
    public void testeDeveAcelerarCorretamente() throws Exception {
        System.out.println("testeDeveAcelerarCorretamente");
        // Given
        Carro carro1 = CarroProvider.get();

        // When
        carroService.acelerar(carro1, 20);

        // Then
        assertEquals(20, carro1.getVelocidadeAtual());
    }

    @Test
    @DisplayName("O carro deve frear corretamente")
    public void testeDeveFrearCorretamente() throws Exception {
        System.out.println("testeDeveFrearCorretamente");
        // Given
        Carro carro1 = CarroProvider.get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 20);
        carroService.frear(carro1, 10);

        // Then

        assertTrue(true);

        assertEquals(10, carro1.getVelocidadeAtual());
    }

    @Test
    @DisplayName("A velocidade do carro não pode ser negativa")
    public void testeVelocidadeNaoPodeSerNegativa() throws Exception {
        System.out.println("testeVelocidadeNaoPodeSerNegativa");

        // Given
        Carro carro1 = CarroBuilder.retonarCarroBuilder().get();

        // When
        carroService.acelerar(carro1, 20);
        carroService.frear(carro1, 20);
        carroService.frear(carro1, 20);

        // Then
        assertEquals(carro1.getVelocidadeAtual(), 0);
    }

    @Test
    @DisplayName("O carro não deve acelerar desligado")
    public void testeNaoDeveAcelerarDesligado() throws Exception {
        System.out.println("testeNaoDeveAcelerarDesligado");

        // Given
        Carro carro1 = CarroBuilder.retonarCarroBuilder().get();


        // When
        carroService.acelerar(carro1, 20);

        // Then
        assertFalse(carro1.isLigado());
        assertEquals(carro1.getVelocidadeAtual(), 0);
    }

    @Test
    public void deveDesligarOCarroCorretamente() throws Exception {
        System.out.println("deveDesligarOCarroCorretamente");

        // Given
        Carro carro1 = CarroBuilder.retonarCarroBuilder().get();

        // When
        carroService.ligar(carro1);
        carroService.desligar(carro1);

        // Then
        assertFalse(carro1.isLigado());
        assertEquals(carro1.getVelocidadeAtual(), 0);
    }

    @Test
    public void deveRetornarOEstadoAtualCorretamente() throws Exception {
        System.out.println("deveRetornarOEstadoAtualCorretamente");

        // Given
        Carro carro1 = CarroBuilder.retonarCarroBuilder().get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 100);

        // Then
        assertEquals("O carro está ligado: true e a velocidade atual é: 100", carroService.estadoAtual(carro1));
    }

    @Test
    public void testMultiplosAsserts() {
        Carro carro = new Carro("vermelho", "Fiat", "2010", "Uno", 500);

        assertEquals(carro.getCor(), "vermelho");
        assertEquals(carro.getAno(), "2010");
        assertEquals(carro.getCavalos(), 500);
    }

    @Test
    public void testMultiplosAsserts_AssertAll() {

        Carro carro = new Carro("vermelho", "Fiat", "2010", "Uno", 500);

        assertAll("Teste atributos carro",
                () -> assertEquals(carro.getCor(), "vermelho"),
                () -> assertEquals(carro.getAno(), "2010"),
                () -> assertEquals(carro.getCavalos(), 500)
                );
    }

    @Test
    public void deveLancarUmaExceptionQuandoAceleraValorNegativo() {
        Carro carro = CarroProvider.get();

        Throwable throwable = assertThrows(Exception.class,
                () -> carroService.acelerar(carro, -1)
        );

        assertEquals("A velocidade deve ser maior que zero", throwable.getMessage());
    }

    @Test
    public void deveLancarUmaExceptionQuandoFrearValorNegativo() {
        Carro carro = CarroProvider.get();

        Throwable throwable = assertThrows(Exception.class,
                () -> carroService.acelerar(carro, -1)
        );

        assertEquals("A velocidade deve ser maior que zero", throwable.getMessage());
    }
//    @Test
//    public void deveLancarUmaExceptionQuandoAceleraValorNegativo() {
//        Carro carro = CarroProvider.get();
//
//        try {
//            carroService.acelerar(carro, 0);
//            fail("Não lancou a exception");
//        } catch (Exception e) {
//            assertEquals(e.getMessage(), "A velocidade deve ser maior que zero");
//        }
//    }
//
//    @Test(expected = Exception.class)
//    public void deveLancarUmaExceptionQuandoAceleraValorNegativo_2() throws Exception {
//        Carro carro = CarroProvider.get();
//
//        carroService.acelerar(carro, -10);
//    }
//
//    @Test
//    public void deveLancarUmaExceptionQuandoAceleraValorNegativo_3() throws Exception {
//        Carro carro = CarroProvider.get();
//
//        // expect
//        expectedException.expect(Exception.class);
//        expectedException.expectMessage("A velocidade deve ser maior que zero");
//
//        carroService.acelerar(carro, 0);
//    }
//
//    @Test
//    public void deveLancarUmaExceptionQuandoFrearValorNegativo() {
//        Carro carro = CarroProvider.get();
//
//        try {
//            carroService.frear(carro, 0);
//            fail("Deveria lancar exception");
//        } catch (Exception exception) {
//            assertThat(exception.getMessage(), is("Impossivel frear valor menor que 1!"));
//        }
//    }
//
//    @Test(expected = Exception.class)
//    public void deveLancarUmaExceptionQuandoFrearValorNegativo_2() throws Exception {
//
//        Carro carro = CarroProvider.get();
//
//        carroService.frear(carro, -1);
//
//    }
//
//    @Rule
//    public ExpectedException expectedException = ExpectedException.none();
//
//    @Test
//    public void deveLancarUmaExceptionQuandoFrearValorNegativo_3() throws Exception {
//        Carro carro = CarroProvider.get();
//
//        // expect
//        expectedException.expect(Exception.class);
//        expectedException.expectMessage("Impossivel frear valor menor que 1!");
//
//        carroService.frear(carro, -1);
//    }
}
