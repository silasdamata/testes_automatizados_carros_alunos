package builder;

import model.Carro;

import java.io.CharArrayReader;

public class CarroProvider {

    public static Carro get() {
        return new Carro("cor", "marca", "ano", "modelo", 100);
    }

    public static Carro getCarroLigado() {
        Carro carro = new Carro("cor", "marca", "ano", "modelo", 100);
        carro.setLigado(true);
        return carro;
    }

    public static Carro getCarroComCavalos(int cavalos) {
        return new Carro("cor", "marca", "ano", "modelo", cavalos);
    }

}
