package com.example.lukasz.productscanandcompare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
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
    private WebView webView;
    private String linkurl = "";

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
            webView = (WebView) findViewById(R.id.webView);
            additem.setEnabled(false);
            baza = new SqliteDatabase(this);
            if(getIntent().getExtras() != null)
            {
                linkurl = getIntent().getExtras().getString("NAZWA");
                formatTxt.setText (linkurl);
                linkurl = getIntent().getExtras().getString("LINKURL");
                contentTxt.setText ("Kod kreskowy: " + linkurl);
                webView.getSettings().setPluginState(WebSettings.PluginState.ON);
                webView.getSettings().setJavaScriptEnabled(true);
                webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
                webView.loadUrl("https://www.google.pl/search?q=" + linkurl);
            }

            if (Funkcje.getBlokada())
            {
                Funkcje.setBlokada(false);
                IntentIntegrator scanIntegrator = new IntentIntegrator(this);
                scanIntegrator.setOrientationLocked(false);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Podaj nazwę");
        final EditText input = new EditText(this);

        try
        {
            input.setInputType(InputType.TYPE_CLASS_TEXT);
            builder.setView(input);

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    nazwa = input.getText().toString();
                    if (nazwa.length() > 65)
                    {
                        Toast toast = Toast.makeText(getApplicationContext(), "Zbyt długa ilość znaków. Maksymalna ilość to 65", Toast.LENGTH_SHORT);
                        dialog.cancel();
                        toast.show();
                    }
                    else
                    {
                        additem.setEnabled(false);
                        baza.dodajProdukt(nazwa, kod);
                        finish();
                        Toast toast = Toast.makeText(getApplicationContext(), "Dodano do listy", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
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
            contentTxt.setText ("Kod kreskowy: " + scanningResult.getContents());
            formatTxt.setText ("Typ kodu: " + scanningResult.getFormatName());
            webView.getSettings().setPluginState(WebSettings.PluginState.ON);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(false);
            webView.loadUrl("https://www.google.pl/search?q=" + scanningResult.getContents());
        }
        else if (resultCode == RESULT_CANCELED)
        {
            this.finish();
        }
    }
}
