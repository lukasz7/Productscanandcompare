package com.example.lukasz.productscanandcompare;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


/**
 * Created by Lukasz on 2017-10-24.
 */

public class SqliteDatabase extends SQLiteOpenHelper
{
    public SqliteDatabase (Context context)
    {
        super(context, "products.db", null, 1);
    }

    public void onCreate(SQLiteDatabase db)
    {
        try
        {
            db.execSQL("create table produkty(" +
                    "id_produktu integer primary key autoincrement," +
                    "nazwa text null," +
                    "kod text null);");
        }
        catch (Exception ex)
        {
            /*Toast toast = Toast.makeText(, ex.getMessage(), Toast.LENGTH_LONG);
            toast.show();*/
        }
    }

    public void onUpgrade (SQLiteDatabase db, int oldVersion, int netVersion)
    {

    }

    public void dodajProdukt (String nazwa, String kod)
    {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put("nazwa", nazwa);
        wartosci.put("kod", kod);
        db.insertOrThrow("produkty", null, wartosci);
    }

    public Produkt[] pokazProdukty ()
    {
        List<Produkt> produkty = new LinkedList<Produkt>();
        String[] kolumny={"id_produktu","nazwa","kod"};
        SQLiteDatabase db = getReadableDatabase();
        Cursor kursor = db.query("produkty", kolumny, null, null, null, null, null);

        while(kursor.moveToNext())
        {
            Produkt produkt = new Produkt();
            produkt.setId(kursor.getLong(0));
            produkt.setNazwa(kursor.getString(1));
            produkt.setKod(kursor.getString(2));
            produkty.add(produkt);
        }

        Produkt[] lista_produktow = produkty.toArray(new Produkt[produkty.size()]);

        return lista_produktow;
    }
}
