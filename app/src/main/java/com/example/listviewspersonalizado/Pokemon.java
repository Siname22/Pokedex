package com.example.listviewspersonalizado;

public class Pokemon {
    //variables con imagen y con drawable
    String nombre;
    int imgPokemon;

    public Pokemon(String nombre) {
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }

    public int getImgPokemon() {
        return imgPokemon;
    }
}
