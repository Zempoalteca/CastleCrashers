package com.example.zaira.castlecrashers;

public class Jugador {

    private String nombre;
    private int imagen;
    private int nivel;

    public Jugador(String nombre, int imagen, int nivel) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.nivel = nivel;
    }

    public String getNombre() {
        return nombre;
    }

    public int getImagen() {
        return imagen;
    }

    public int getNivel() {
        return nivel;
    }
}
