package service;

import builder.CarroProvider;
import model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

public class CarroServiceImplTest2 {

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
        carroService.acelerar(carro1, 20);

        // Then
        assertEquals(20, carro1.getVelocidadeAtual());

        Mockito.verify(sistemaDeSeguranca).travaDeEmergenciaAtivada(Mockito.any(Carro.class));
        Mockito.verify(sistemaDeSeguranca).travaDeEmergenciaAtivada(carro1);
        Mockito.verify(sistemaDeSeguranca, Mockito.times(1)).travaDeEmergenciaAtivada(carro1);
        Mockito.verify(sistemaDeSeguranca, Mockito.atLeastOnce()).travaDeEmergenciaAtivada(carro1);
    }


//    @Test
//    @Disabled
//    public void deveRetornarOEstadoAtualCorretamente() throws Exception {
//        System.out.println("deveRetornarOEstadoAtualCorretamente");
//
//        // Given
//        Carro carro1 = CarroBuilder.retonarCarro().get();
//
//        // When
//        carroService.ligar(carro1);
//        carroService.acelerar(carro1, 100);
//
//        // Then
//        assertEquals("O carro está ligado: true e a velocidade atual é: 100", carroService.estadoAtual(carro1));
//    }

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

    @Test
    public void naoDeveLigarCarroComSistemaDeSegurancaAtivo() throws Exception {
        Carro carro = CarroProvider.get();

        Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(Mockito.any(Carro.class))).thenReturn(true);
        //Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(carro)).thenReturn(true);

        Throwable throwable = assertThrows(Exception.class,
                () -> carroService.ligar(carro)
        );

        assertEquals("Carro bloqueado!", throwable.getMessage());
    }

    @Test
    public void naoDeveLigarCarroComSistemaDeSegurancaAtivo2() throws Exception {
        Carro carro = CarroProvider.get();

        Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(Mockito.any(Carro.class))).thenThrow(new Exception("Erro!!!"));
        //Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(carro)).thenReturn(true);

        Throwable throwable = assertThrows(Exception.class,
                () -> carroService.ligar(carro)
        );

        assertEquals("Erro!!!", throwable.getMessage());
    }



}
