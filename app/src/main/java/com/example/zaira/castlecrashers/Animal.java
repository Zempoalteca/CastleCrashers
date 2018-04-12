package com.example.zaira.castlecrashers;

public class Animal {

    private int id_animal;
    private String nombre;
    private int imagen;
    private int nivel;
    private double latitud;
    private double longitud;

    public Animal(int id_animal, String nombre, int imagen, int nivel, double latitud, double longitud) {
        this.id_animal = id_animal;
        this.nombre = nombre;
        this.imagen = imagen;
        this.nivel = nivel;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public int getId_animal() {
        return id_animal;
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
