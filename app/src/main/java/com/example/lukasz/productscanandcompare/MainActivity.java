package com.example.lukasz.productscanandcompare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.content.Intent;


public class MainActivity extends AppCompatActivity
{
    private SqliteDatabase baza;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        baza = new SqliteDatabase(this);
        try
        {
            ListView listView = (ListView) findViewById (R.id.listview);

            ProduktAdapter adapter = new ProduktAdapter(this,
                    R.layout.wiersz, baza.pokazProdukty());

            listView.setAdapter(adapter);
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void onResume()
    {
        super.onResume();
        setContentView(R.layout.activity_main);
        try
        {
            ListView listView = (ListView) findViewById (R.id.listview);

            ProduktAdapter adapter = new ProduktAdapter(this,
                    R.layout.wiersz, baza.pokazProdukty());

            listView.setAdapter(adapter);
        }
        catch (Exception ex)
        {

        }
    }

    public void btn_scan_click(View v)
    {
        try
        {
            Intent newintent = new Intent(this, Podglad.class);
            startActivity(newintent);
        }
        catch (Exception ex)
        {

        }
    }

    public void btn_wysw(View v)
    {
        try
        {
            ListView listView = (ListView) findViewById (R.id.listview);

            ProduktAdapter adapter = new ProduktAdapter(this,
                    R.layout.wiersz, baza.pokazProdukty());

            listView.setAdapter(adapter);
        }
        catch (Exception ex)
        {

        }
    }


}
