package service;

import model.Carro;

public class CarroServiceImpl implements CarroService {

    @Override
    public void acelerar(Carro carro, int velocidadeAMais) throws Exception {
        if(velocidadeAMais <= 0) {
            throw new Exception("A velocidade deve ser maior que zero");
        }

        if(carro.isLigado()) {
            carro.setVelocidadeAtual(carro.getVelocidadeAtual() + velocidadeAMais);
        }
    }

    @Override
    public void frear(Carro carro, int velocidadeAMenos) throws Exception {
        if(velocidadeAMenos <= 0) {
            throw new Exception("Impossivel frear valor menor que 1!");
        }

        if(velocidadeAMenos >= carro.getVelocidadeAtual()) {
            carro.setVelocidadeAtual(0);
        } else {
            carro.setVelocidadeAtual(carro.getVelocidadeAtual() - velocidadeAMenos);
        }
    }

    @Override
    public void ligar(Carro carro) {
        carro.setLigado(true);
    }

    @Override
    public void desligar(Carro carro) {
        carro.setLigado(false);
    }

    @Override
    public String estadoAtual(Carro carro) {
        return "O carro está ligado: " +
                carro.isLigado() +
                " e a velocidade atual é: " +
                carro.getVelocidadeAtual();
    }
}
