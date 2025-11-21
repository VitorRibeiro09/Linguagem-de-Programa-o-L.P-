package com.djavan.navio_crud.model;

public class Navio {
    private int id;
    private String nome;
    private Double tonelagem;
    private String destino;

    // Construtor completo
    public Navio(int id, String nome, Double tonelagem, String destino) {
        this.id = id;
        this.nome = nome;
        this.tonelagem = tonelagem;
        this.destino = destino;
    }

    // Construtor sem ID (para inserção)
    public Navio(String nome, Double tonelagem, String destino) {
        this.nome = nome;
        this.tonelagem = tonelagem;
        this.destino = destino;
    }

    // Método da música
    public String navegar() {
        return "O navio " + nome + " de " + tonelagem + " toneladas está navegando em direção a " + destino + ".";
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Double getTonelagem() {
        return tonelagem;
    }

    public void setTonelagem(Double tonelagem) {
        this.tonelagem = tonelagem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Tonelagem: " + tonelagem + ", Destino: " + destino;
    }
}
