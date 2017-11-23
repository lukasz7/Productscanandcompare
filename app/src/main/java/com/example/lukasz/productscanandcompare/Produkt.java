package com.example.lukasz.productscanandcompare;

/**
 * Created by Lukasz on 2017-10-25.
 */

public class Produkt
{
    private int id;
    private String nazwa;
    private String kod;

    public int getId() {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getNazwa()
    {
        return nazwa;
    }
    public void setNazwa(String nazwa)
    {
        this.nazwa = nazwa;
    }
    public String getKod()
    {
        return kod;
    }
    public void setKod(String kod)
    {
        this.kod = kod;
    }
}
