package com.example.lukasz.productscanandcompare;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.*;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
{
    private SqliteDatabase baza;
    ListView listView;// = (ListView) findViewById (R.id.listview);

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById (R.id.listview);
        //listView.Mode
        baza = new SqliteDatabase(this);

        try
        {
            ProduktAdapter adapter = new ProduktAdapter(this, R.layout.wiersz, baza.pokazProdukty());
            listView.setAdapter(adapter);
            Log.d("tag_1","before setOnItemClickListener");
            Log.d("tag_2","after setOnItemClickListener");
            //registerForContextMenu(listView);
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

            ProduktAdapter adapter = new ProduktAdapter(this, R.layout.wiersz, baza.pokazProdukty());

            listView.setAdapter(adapter);
            registerForContextMenu(listView);
        }
        catch (Exception ex)
        {

        }
    }

    public void btn_scan_click(View v)
    {
        try
        {
            Funkcje.setBlokada(true);
            Intent newintent = new Intent(this, Podglad.class);
            startActivity(newintent);
        }
        catch (Exception ex)
        {

        }
    }

    public void btn_odswiez(View v)
    {
        try
        {
            ListView listView = (ListView) findViewById (R.id.listview);

            ProduktAdapter adapter = new ProduktAdapter(this, R.layout.wiersz, baza.pokazProdukty());

            listView.setAdapter(adapter);
            registerForContextMenu(listView);
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, v.getId(), 0, "Otwórz");
        menu.add(0, v.getId(), 0, "Usuń pozycję");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        ProduktAdapter.RowBeanHolder produkt = (ProduktAdapter.RowBeanHolder) info.targetView.getTag();

        if(item.getTitle()=="Usuń pozycję"){
            if (baza.usunProdukt(Integer.parseInt(produkt.txtId.getText().toString())))
            {
                Toast.makeText(MainActivity.this, "Pozycja została usunięta.", Toast.LENGTH_SHORT).show();
                btn_odswiez(null);
            }
            else
                Toast.makeText(this, "Wystąpił problem z usunięciem pozycji.", Toast.LENGTH_SHORT).show();
        }

        if(item.getTitle()=="Otwórz"){
            try
            {
                Funkcje.setBlokada(false);
                Intent newintent = new Intent(this, Podglad.class);
                newintent.putExtra("LINKURL", produkt.txtKod.getText().toString());
                newintent.putExtra("NAZWA", produkt.txtNazwa.getText().toString());
                startActivity(newintent);
            }
            catch (Exception ex)
            {

            }
        }
        return super.onContextItemSelected(item);
    }
}
