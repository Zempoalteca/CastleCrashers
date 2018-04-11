package com.example.zaira.castlecrashers;

public class Animal {

    private String nombre;
    private int imagen;
    private int nivel;
    private double latitud;
    private double longitud;

    public Animal(String nombre, int imagen, int nivel, double latitud, double longitud) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.nivel = nivel;
        this.latitud = latitud;
        this.longitud = longitud;
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

    public double getLatitud() {
        return latitud;
    }

    public double getLongitud() {
        return longitud;
    }
}
