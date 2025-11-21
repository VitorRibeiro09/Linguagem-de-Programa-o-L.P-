package com.djavan.navio_crud.model;

public class Mar {
    private int id;
    private Double profundidade;
    private String cor;
    private String estado;

    // Construtor completo
    public Mar(int id, Double profundidade, String cor, String estado) {
        this.id = id;
        this.profundidade = profundidade;
        this.cor = cor;
        this.estado = estado;
    }

    // Construtor sem ID (para inserção)
    public Mar(Double profundidade, String cor, String estado) {
        this.profundidade = profundidade;
        this.cor = cor;
        this.estado = estado;
    }

    // Método da música
    public String mudarEstado() {
        String novoEstado = (this.estado.equalsIgnoreCase("calmo")) ? "agitado" : "calmo";
        this.estado = novoEstado;
        return "O mar de cor " + cor + " e profundidade " + profundidade + "m mudou seu estado para " + novoEstado + ".";
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getProfundidade() {
        return profundidade;
    }

    public void setProfundidade(Double profundidade) {
        this.profundidade = profundidade;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Profundidade: " + profundidade + ", Cor: " + cor + ", Estado: " + estado;
    }
}
