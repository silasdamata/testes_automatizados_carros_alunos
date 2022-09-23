package service;

import builder.CarroBuilder;
import model.Carro;
import org.junit.*;

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
        System.out.println("before");
    }

    @Test
    @Ignore
    public void testeDeveAcelerarCorretamente() {
        System.out.println("testeDeveAcelerarCorretamente");
        // Given
        Carro carro1 = CarroBuilder.retonarCarro().ligado().get();

        // When
        carroService.acelerar(carro1, 20);

        // Then
        Assert.assertEquals(20, carro1.getVelocidadeAtual());
    }

    @Test
    public void testeDeveFrearCorretamente() {
        System.out.println("testeDeveFrearCorretamente");
        // Given
        Carro carro1 = CarroBuilder.retonarCarro().get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 20);
        carroService.frear(carro1, 10);

        // Then
        Assert.assertEquals(10, carro1.getVelocidadeAtual());
    }

    @Test
    public void testeVelocidadeNaoPodeSerNegativa() {
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
    public void testeNaoDeveAcelerarDesligado() {
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
    public void deveRetornarOEstadoAtualCorretamente() {
        System.out.println("deveRetornarOEstadoAtualCorretamente");

        // Given
        Carro carro1 = CarroBuilder.retonarCarro().get();

        // When
        carroService.ligar(carro1);
        carroService.acelerar(carro1, 100);

        // Then
        Assert.assertEquals("O carro está ligado: true e a velocidade atual é: 100", carroService.estadoAtual(carro1));
    }




//
//    @Rule
//    public ErrorCollector errorCollector = new ErrorCollector();
//
//    @Test
//    public void errorColectorTests() {
//        // Given
//        CarroService carroService = new CarroServiceImpl();
//        Carro carro1 =
//                new Carro("prata", "GM", "2012", "Celta");
//
//        // When
//        carroService.ligar(carro1);
//        carroService.acelerar(carro1, 10);
//
//        errorCollector.checkThat(carro1.getVelocidadeAtual(), is(10));
//        errorCollector.checkThat(carro1.getVelocidadeAtual(), is(not(20)));
//        errorCollector.checkThat(carro1.getVelocidadeAtual(), equalTo(10));
//    }

//    @Test
//    public void asserts() {
//
//        boolean eMaiorDeIdade = true;
//        Assert.assertFalse(false);
//        Assert.assertEquals(true, eMaiorDeIdade);
//        Assert.assertEquals(1, 1);
//        Carro carro1 =
//                new Carro("prata", "GM", "2012", "Celta");
//
//        Carro carro2 =
//                new Carro("prata", "GM", "2012", "Celta");
//
//        CarroService carroService = new CarroServiceImpl();
//        carroService.ligar(carro2);
//        carroService.acelerar(carro2, 10);
//
//        Assert.assertEquals(carro1, carro2);
//        Assert.assertSame(carro1, carro1);
//
//        Assert.assertEquals(1, 1);
//
//        // assertEquals(double, double, double)
//        Assert.assertEquals(10.10555555, 10.108888, 0.01);
//        // 3.222222222222222222222222222222222222222222222222222221
//        // 3.22222222222222222222222222222222222222222222222222222444
//
//        Assert.assertNotEquals(1, 2);
//
//        Carro c3 = null;
//
//        Assert.assertNull(c3);
//
//
//        // teste 01
//        Assert.assertTrue(eMaiorDeIdade);
//
//        boolean eMenorDeIdade = false;
//
//        // teste 02
//        Assert.assertTrue(!eMenorDeIdade);
//        Assert.assertFalse(eMenorDeIdade);
//    }

//    @Test
//    public void assertThat() {
//        // verifique que:
//
//        // Given
//        CarroService carroService = new CarroServiceImpl();
//        Carro carro1 =
//                new Carro("prata", "GM", "2012", "Celta");
//
//        // When
//        carroService.ligar(carro1);
//        carroService.acelerar(carro1, 10);
//
//        //
//        Assert.assertEquals(10, carro1.getVelocidadeAtual());
//        Assert.assertThat(carro1.getVelocidadeAtual(), is(equalTo(10)));
//        //
//
//
//        Assert.assertThat(carro1.getVelocidadeAtual(), is(not(20)));
//        // actual - expected
//
//
//    }

//    @Test
//    public void assertDuvidas() {
//        // Given
//        Carro carro1 =
//                new Carro("prata", "GM", "2012", "Celta");
//
//        Carro carro2 =
//                new Carro("azul", "GM", "2012", "Celta");
//
//
//        //Assert.assertEquals(carro1, carro2); // 1 - assertEquals(Object, Object)
//        Assert.assertEquals(1, 1); // 2 - assertEquals(int, int)
//        Assert.assertEquals(1.0, 1.0, 0.1); // 3 - assertEquals(double, double, double)
//        Assert.assertEquals(1L, 1L); // 4 - assertEquals(long, long)
//        Assert.assertEquals(1L, 1L); // 5 - assertEquals(boolean, boolean)
//
//        Assert.assertEquals(0, carro1.getVelocidadeAtual());
//        Assert.assertEquals(0, 0);
//        Assert.assertEquals(false, carro1.isLigado());
//
//        carro1.equals(carro2);
//
//        Assert.assertEquals(false, false);
//    }


//    @Test
//    public void testeValidaPrimeiroLugarF1() {
//        // Given
//        Carro ferrari01 =
//                new Carro("prata", "ferrari", "2020", "ferrari");
//        Carro ferrari02 =
//                new Carro("prata", "ferrari", "2020", "ferrari");
//        Carro maclaren01 =
//                new Carro("prata", "maclaren", "2020", "maclaren");
//        Carro maclaren02 =
//                new Carro("prata", "maclaren", "2020", "maclaren");
//
//
//        // When
//        // service
//
//
//        // Then
//        Assert.assertSame(ferrari01, ferrari01);
//    }
}
