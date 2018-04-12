package com.example.zaira.castlecrashers;

public class Jugador {

    private int id_jugador;
    private String nombre;
    private int imagen;
    private int nivel;

    public Jugador(int id_jugador, String nombre, int imagen, int nivel) {
        this.id_jugador = id_jugador;
        this.nombre = nombre;
        this.imagen = imagen;
        this.nivel = nivel;
    }

    public int getId_jugador() {
        return id_jugador;
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
