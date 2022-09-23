package model;

import java.util.Objects;

/**
 * Um carro tem os seguintes atributos:
 * - cor
 * - marca
 * - modelo
 * - ano
 * - ligado
 * - velocidadeAtual
 * <p>
 * Enquanto o carro estiver desligado deve ser capaz de:
 * - Ligar
 * - Motrar estado atual
 * <p>
 * Enquanto o carro estiver ligado deve ser capaz de:
 * - Desligar
 * - Acelerar
 * - Frear
 * - Motrar estado atual
 * <p>
 * Regras:
 * - Só podemos realizar as ações com o carro ligado
 * - Só podemos desligar o carro quando ele parar (velocidadeAtual = 0)
 * - Não existe velocidade negativa
 */

public class Carro {

    private String cor;
    private String marca;
    private String ano;
    private String modelo;
    private boolean ligado;
    private int velocidadeAtual;
    private int cavalos;

    public Carro(String cor, String marca, String ano, String modelo, int cavalos) {
        this.cor = cor;
        this.marca = marca;
        this.ano = ano;
        this.modelo = modelo;
        this.ligado = false;
        this.velocidadeAtual = 0;
        this.cavalos = cavalos;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCavalos() {
        return cavalos;
    }

    public void setCavalos(int cavalos) {
        this.cavalos = cavalos;
    }

    public boolean isLigado() {
        return ligado;
    }

    public void setLigado(boolean ligado) {
        this.ligado = ligado;
    }

    public int getVelocidadeAtual() {
        return velocidadeAtual;
    }

    public void setVelocidadeAtual(int velocidadeAtual) {
        this.velocidadeAtual = velocidadeAtual;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "cor='" + cor + '\'' +
                ", marca='" + marca + '\'' +
                ", ano='" + ano + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ligado=" + ligado +
                ", velocidadeAtual=" + velocidadeAtual +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Carro)) return false;
        Carro carro = (Carro) o;
        return
                cor.equals(carro.cor) &&
                        marca.equals(carro.marca) &&
                        ano.equals(carro.ano) &&
                        modelo.equals(carro.modelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cor, marca, ano, modelo);
    }
}
