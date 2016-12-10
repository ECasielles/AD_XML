package com.mercacortex.ecasielles_ad_xml.activity;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.adapter.EmpleadoAdapterRecycler;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Empleados extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton fabCalcular;
    ViewGroup parentLayout;
    EmpleadoAdapterRecycler adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
        parentLayout = (ViewGroup) findViewById(R.id.activity_empleados);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        try {
            fabCalcular = (FloatingActionButton) findViewById(R.id.fabCalcular);

            adapter = new EmpleadoAdapterRecycler(this);
            recyclerView = (RecyclerView) findViewById(R.id.recyclerViewEmpleados);

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

            fabCalcular.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    try {
                        builder.setMessage(
                                "La edad media es: " + String.valueOf(adapter.mediaEdad()) + "\n" +
                                "La edad máxima es: " + String.valueOf(adapter.maxEdad()) + "\n" +
                                "La edad mínima es: " + String.valueOf(adapter.minEdad()) + "\n" +
                                "El salario medio es: " + String.valueOf(adapter.mediaSalario()) + "\n" +
                                "El salario máximo es: " + String.valueOf(adapter.maxSalario()) + "\n" +
                                "El salario mínimo es: " + String.valueOf(adapter.minSalario())
                        );
                        builder.show();
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
