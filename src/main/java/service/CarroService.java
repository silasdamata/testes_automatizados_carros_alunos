package service;

import model.Carro;

public interface CarroService {

    void acelerar(Carro carro, int velocidadeAMais) throws Exception;
    void frear(Carro carro, int velocidadeAMenos) throws Exception;
    void ligar(Carro carro) throws Exception;
    void desligar(Carro carro);
    String estadoAtual(Carro carro);

}
