package com.example.lukasz.productscanandcompare;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class MainActivity extends AppCompatActivity {

    private TextView formatTxt, contentTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        formatTxt = (TextView)findViewById(R.id.scan_format);
        contentTxt = (TextView)findViewById(R.id.scan_content);

    }

    public void btn_scan_click(View v)
    {
        formatTxt.setText("Format");
        contentTxt.setText("Content");
        IntentIntegrator scanIntegrator = new IntentIntegrator(this);
        scanIntegrator.initiateScan();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {

        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);

        if (scanningResult != null) {

            //Wynik skanowania
            String scanContent = scanningResult.getContents();

            //Format kodu skanowania
            String scanFormat = scanningResult.getFormatName();

            formatTxt.setText("Format: " + scanFormat);
            contentTxt.setText("Content: " + scanContent);

        }

        else{

            formatTxt.setText("Format");
            contentTxt.setText("Content");

            Toast toast = Toast.makeText(getApplicationContext(), "Brak danych!", Toast.LENGTH_SHORT);
            toast.show();

        }

    }
}
