package com.mercacortex.ecasielles_ad_xml.activity;


import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.adapter.EmpleadoAdapter;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Empleados extends AppCompatActivity {

    ListView listView;
    Button btnCalcular;
    ViewGroup parentLayout;
    EmpleadoAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
        parentLayout = (ViewGroup) findViewById(R.id.activity_empleados);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        try {
            btnCalcular = (Button)findViewById(R.id.btnCalcular);

            adapter = new EmpleadoAdapter(this);
            listView = (ListView)findViewById(R.id.listViewEmpleados);
            listView.setAdapter(adapter);

            btnCalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        builder.setMessage("La edad media es: " + String.valueOf(adapter.mediaEdad()) + "\n" +
                                "El salario medio es: " + String.valueOf(adapter.mediaSalario()) + "\n" +
                                "El salario máximo es: " + String.valueOf(adapter.maxSalario()) + "\n" +
                                "El salario mínimo es: " + String.valueOf(adapter.minSalario()));
                    } catch (Exception e) {
                        Snackbar.make(parentLayout, "Error: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
                    }
                }
            });
        } catch (XmlPullParserException e) {
            Snackbar.make(parentLayout, "Error: Documento XML mal formado.", Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            Snackbar.make(parentLayout, "Error: No se puede acceder al fichero XML.", Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            Snackbar.make(parentLayout, "Error: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
        }
    }
}
