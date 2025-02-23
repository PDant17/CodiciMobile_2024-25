package it.unisa.di.mp.socketjsoup;

public class Squadra {
    private String nome;
    private int punti;

    public Squadra(String n, int punti) {
            this.nome = n;
            this.punti = punti;
    }

    public String getName() {
            return nome;
    }

    public int getPunti() {
            return punti;
    }
}
