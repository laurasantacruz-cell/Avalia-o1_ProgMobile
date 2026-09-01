package com.example.avaliacao1;

public class Bioma {

    private final String nome;
    private final String descricao;
    private final int imagemPrincipal;
    private final int imagemSecundaria;

    public Bioma(String nome, String descricao, int imagemPrincipal, int imagemSecundaria) {
        this.nome = nome;
        this.descricao = descricao;
        this.imagemPrincipal = imagemPrincipal;
        this.imagemSecundaria = imagemSecundaria;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getImagemPrincipal() {
        return imagemPrincipal;
    }

    public int getImagemSecundaria() {
        return imagemSecundaria;
    }
}