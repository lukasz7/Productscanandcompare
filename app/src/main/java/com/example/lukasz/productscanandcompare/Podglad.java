package com.example.lukasz.productscanandcompare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class Podglad extends AppCompatActivity {

    private TextView formatTxt, contentTxt;
    private Button additem;
    private SqliteDatabase baza;
    private String nazwa = "b/d";
    private String kod = "b/d";
    private Boolean skaner = true;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        try
        {
            setContentView(R.layout.activity_podglad);
            formatTxt = (TextView)findViewById(R.id.textViewNazwa);
            contentTxt = (TextView)findViewById(R.id.textViewKod);
            additem = (Button)findViewById(R.id.addButton);
            additem.setEnabled(false);
            baza = new SqliteDatabase(this);

            if (skaner)
            {
                skaner = false;
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.initiateScan();
            }
        }
        catch(Exception ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }

    public void addButtonClick (View view)
    {
        try
        {
            additem.setEnabled(false);
            baza.dodajProdukt(nazwa, kod);
            finish();
            Toast toast = Toast.makeText(getApplicationContext(), "Dodano do listy", Toast.LENGTH_SHORT);
            toast.show();
        }
        catch (Exception ex)
        {
            Toast toast = Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG);
            toast.show();
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (resultCode == RESULT_OK)
        {
            nazwa = scanningResult.getFormatName();
            kod = scanningResult.getContents();
            additem.setEnabled(true);
            contentTxt.setText ("Kod: " + scanningResult.getContents());
            formatTxt.setText ("Nazwa: " + scanningResult.getFormatName());
        }
        else if (resultCode == RESULT_CANCELED)
        {
            this.finish();
        }
    }
}
