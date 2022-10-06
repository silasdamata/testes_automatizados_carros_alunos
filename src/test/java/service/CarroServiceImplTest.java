package service;

import builder.CarroBuilder;
import builder.CarroProvider;
import model.Carro;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class CarroServiceImplTest {
    // F.I.R.S.T
    // F - Fast
    // I - Isolado/Independente
    // R - Repetable
    // S - Self-validating
    // T - Oportuno (TDD)

    // D.R.Y - Don't Repeat Yourself

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

    @Mock
    private SistemaDeSeguranca sistemaDeSeguranca;

    @Mock
    private GPS gps;

    @InjectMocks
    private CarroServiceImpl carroService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testeDeveAcelerarCorretamente() throws Exception {
        System.out.println("testeDeveAcelerarCorretamente");
        // Given
        Carro carro1 = CarroProvider.get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 10);
        carroService.acelerar(carro1, 10);

        Mockito.verify(sistemaDeSeguranca, Mockito.times(1)).travaDeEmergenciaAtivada(Mockito.any(Carro.class));
        Mockito.verify(gps, Mockito.atLeastOnce()).enviarLocalizacao();
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
        assertEquals(10, carro1.getVelocidadeAtual());
    }

    @Test
    @DisplayName("A velocidade do carro não pode ser negativa")
    public void testeVelocidadeNaoPodeSerNegativa() throws Exception {
        System.out.println("testeVelocidadeNaoPodeSerNegativa");

        // Given
        Carro carro1 = CarroProvider.get();

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
        Carro carro1 = CarroProvider.get();


        // When
        Mockito.verify(sistemaDeSeguranca, Mockito.never()).travaDeEmergenciaAtivada(carro1);
        carroService.acelerar(carro1, 20);

        // Then
        assertFalse(carro1.isLigado());
        assertEquals(carro1.getVelocidadeAtual(), 0);
    }

    @Test
    public void deveDesligarOCarroCorretamente() throws Exception {
        System.out.println("deveDesligarOCarroCorretamente");

        // Given
        Carro carro1 = CarroProvider.get();

        // When
        carroService.ligar(carro1);
        carroService.desligar(carro1);

        // Then
        assertFalse(carro1.isLigado());
        assertEquals(carro1.getVelocidadeAtual(), 0);
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
        Carro carro =
                CarroBuilder.retonarCarroBuilder().get();

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

    @Test
    void naoDeveLigarOCarroQuandoATravaEstiverAtiva() throws Exception {
        Carro carro = CarroProvider.get();

        Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(carro)).thenReturn(true);

        Throwable throwable = assertThrows(Exception.class,
                () -> carroService.ligar(carro)
        );

        assertEquals("Carro bloqueado!", throwable.getMessage());
    }

    @Test
    public void naoDeveLigarCarroComSistemaDeSegurancaAtivo() throws Exception {
        Carro carro = CarroProvider.get();

        Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(Mockito.any(Carro.class))).thenReturn(true);
        //Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(carro)).thenReturn(true);

        Throwable throwable = assertThrows(Exception.class,
                () -> carroService.ligar(carro)
        );

        assertEquals("Carro bloqueado!", throwable.getMessage());

        Mockito.verify(sistemaDeSeguranca).travaDeEmergenciaAtivada(Mockito.any(Carro.class));
        Mockito.verify(sistemaDeSeguranca).travaDeEmergenciaAtivada(carro);
        Mockito.verify(sistemaDeSeguranca, Mockito.times(1)).travaDeEmergenciaAtivada(carro);
        Mockito.verify(sistemaDeSeguranca, Mockito.atLeastOnce()).travaDeEmergenciaAtivada(carro);
    }
}
