package service;

import model.Carro;

// Deve desligar um carro com a trava e não permitir ligar novamente
public interface SistemaDeSeguranca {
    boolean travaDeEmergenciaAtivada(Carro carro) throws Exception;
}
