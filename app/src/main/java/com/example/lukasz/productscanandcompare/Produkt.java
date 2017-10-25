package com.example.lukasz.productscanandcompare;

/**
 * Created by Lukasz on 2017-10-25.
 */

public class Produkt
{
    private Long id;
    private String nazwa;
    private String kod;

    public Long getId() {
        return id;
    }
    public void setId(Long id)
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
