package model;

/**
 *
 * Um carro tem os seguintes atributos:
 *  - cor
 *  - marca
 *  - modelo
 *  - ano
 *  - ligado
 *  - velocidadeAtual
 *
 *  Enquanto o carro estiver desligado deve ser capaz de:
 *  - Ligar
 *  - Motrar estado atual
 *
 * Enquanto o carro estiver ligado deve ser capaz de:
 * - Desligar
 * - Acelerar
 * - Frear
 * - Motrar estado atual
 *
 * Regras:
 * - Só podemos realizar as ações com o carro ligado
 * - Só podemos desligar o carro quando ele parar (velocidadeAtual = 0)
 * - Não existe velocidade negativa
 *
 */

public class Carro {

    private String cor;
    private String marca;
    private String ano;
    private String modelo;
    private boolean ligado;
    private int velocidadeAtual;

    public Carro(String cor, String marca, String ano, String modelo) {
        this.cor = cor;
        this.marca = marca;
        this.ano = ano;
        this.modelo = modelo;
        this.ligado = false;
        this.velocidadeAtual = 0;
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
}
