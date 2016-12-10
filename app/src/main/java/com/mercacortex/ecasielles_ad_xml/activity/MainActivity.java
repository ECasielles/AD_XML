package com.mercacortex.ecasielles_ad_xml.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.mercacortex.ecasielles_ad_xml.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mbtEj1, mbtEj2, mbtEj3, mbtEj4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mbtEj1 = (Button) findViewById(R.id.btnEmpleados);
        mbtEj2 = (Button) findViewById(R.id.btnTiempo);
        mbtEj3 = (Button) findViewById(R.id.btnBizi);
        mbtEj4 = (Button) findViewById(R.id.btnRSS);

        mbtEj1.setOnClickListener(this);
        mbtEj2.setOnClickListener(this);
        mbtEj3.setOnClickListener(this);
        mbtEj4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intent;
        switch (v.getId()) {
            case R.id.btnEmpleados:
                intent = new Intent(MainActivity.this, Empleados.class);
                startActivity(intent);
                break;
            case R.id.btnTiempo:
                intent = new Intent(MainActivity.this, Tiempo.class);
                startActivity(intent);
                break;
            case R.id.btnBizi:
                intent = new Intent(MainActivity.this, Bizi.class);
                startActivity(intent);
                break;
            case R.id.btnRSS:
                intent = new Intent(MainActivity.this, NoticiasRSS.class);
                startActivity(intent);
                break;
        }

    }
}