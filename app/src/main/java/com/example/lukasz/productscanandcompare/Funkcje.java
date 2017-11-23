package com.example.lukasz.productscanandcompare;

/**
 * Created by Lukasz on 2017-10-25.
 */

public abstract class Funkcje
{
    private static Boolean blokada = true;


    public static Boolean getBlokada() {
        return blokada;
    }

    public static void setBlokada(Boolean blokada) {
        Funkcje.blokada = blokada;
    }
}
