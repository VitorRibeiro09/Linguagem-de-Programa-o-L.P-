package com.djavan.navio_crud.model;

public class Pessoa {
    private int id;
    private String nome;
    private String emocao;
    private String localizacao;

    // Construtor completo
    public Pessoa(int id, String nome, String emocao, String localizacao) {
        this.id = id;
        this.nome = nome;
        this.emocao = emocao;
        this.localizacao = localizacao;
    }

    // Construtor sem ID (para inserção)
    public Pessoa(String nome, String emocao, String localizacao) {
        this.nome = nome;
        this.emocao = emocao;
        this.localizacao = localizacao;
    }

    // Método da música
    public String observarNavio(Navio navio) {
        return nome + " está em " + localizacao + ", sentindo-se " + emocao + ", e observa o navio " + navio.getNome() + ".";
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

    public String getEmocao() {
        return emocao;
    }

    public void setEmocao(String emocao) {
        this.emocao = emocao;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Nome: " + nome + ", Emoção: " + emocao + ", Localização: " + localizacao;
    }
}
