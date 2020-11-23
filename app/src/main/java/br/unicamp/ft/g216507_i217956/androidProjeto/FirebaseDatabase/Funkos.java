package br.unicamp.ft.g216507_i217956.androidProjeto.FirebaseDatabase;

public class Funkos {
    private String marca;
    private String nome;
    private int foto;

    public Funkos(String marca, String nome, int foto) {
        this.marca = marca;
        this.nome = nome;
        this.foto = foto;
    }

    public String getMarca() {
        return marca;
    }

    public String getNome() {
        return nome;
    }

    public int getFoto() {
        return foto;
    }
}

