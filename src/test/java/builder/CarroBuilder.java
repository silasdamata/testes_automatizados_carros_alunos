package builder;

import model.Carro;

public class CarroBuilder {

    private Carro carro;

    public static CarroBuilder retonarCarroBuilder() {
        CarroBuilder carroBuilder = new CarroBuilder();
        carroBuilder.carro = new Carro("cor", "marca", "ano", "modelo", 100);
        return carroBuilder;
    }

    public Carro get() {
        return carro;
    }



    public CarroBuilder ligado() {
        carro.setLigado(true);
        return this;
    }

    public CarroBuilder comCavalos(int cavalos) {
        carro.setCavalos(cavalos);
        return this;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }
}
