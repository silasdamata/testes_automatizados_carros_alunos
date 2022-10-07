package service;

import builder.CarroProvider;
import model.Carro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CarroServiceImplTest3 {


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
    public void testeNãoDeveAcelerarBloqueado() throws Exception {
        System.out.println("testeNãoDeveAcelerarBloqueado");
        // Given
        Carro carro1 = CarroProvider.get();

        // When
        Mockito.when(sistemaDeSeguranca.travaDeEmergenciaAtivada(carro1)).thenReturn(true);
        carroService.acelerar(carro1, 20);

        // Then
        assertEquals(0, carro1.getVelocidadeAtual());

//        Mockito.verify(sistemaDeSeguranca).travaDeEmergenciaAtivada(carro1);

    }
}
