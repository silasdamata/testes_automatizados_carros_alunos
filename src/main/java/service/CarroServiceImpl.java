package service;

import model.Carro;

public class CarroServiceImpl implements CarroService {

    private GPS gps;
    private SistemaDeSeguranca sistemaDeSeguranca;

    @Override
    public void acelerar(Carro carro, int velocidadeAMais) throws Exception {
        if(velocidadeAMais <= 0) {
            throw new Exception("A velocidade deve ser maior que zero");
        }

        if(carro.isLigado()) {
            carro.setVelocidadeAtual(carro.getVelocidadeAtual() + velocidadeAMais);
            gps.enviarLocalizacao();
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
            gps.enviarLocalizacao();
        }
    }

    @Override
    public void ligar(Carro carro) throws Exception {
        if (sistemaDeSeguranca.travaDeEmergenciaAtivada(carro)) {
            throw new Exception("Carro bloqueado!");
        }
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
