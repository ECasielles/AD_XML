package com.mercacortex.ecasielles_ad_xml.activity;

<<<<<<< HEAD
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
=======
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Empleado;
import com.mercacortex.ecasielles_ad_xml.utils.Analisis;
>>>>>>> origin/master

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Empleados extends AppCompatActivity {

<<<<<<< HEAD
    ListView listView;
    Button btnCalcular;
    ViewGroup parentLayout;
    EmpleadoAdapter adapter;
=======
    TextView txvEmpleadosId;
    TextView txvEmpleadosNombre;
    TextView txvEmpleadosApellido;
    TextView txvEmpleadosPuesto;
    TextView txvEmpleadosEdad;
    TextView txvEmpleadosSalario;

    ViewGroup parentLayout;

    Empleado[] Empleados;
>>>>>>> origin/master

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);
<<<<<<< HEAD
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
=======

        parentLayout = (ViewGroup) findViewById(R.id.activity_empleados);

        txvEmpleadosId = (TextView) findViewById(R.id.txvId);
        txvEmpleadosNombre = (TextView) findViewById(R.id.txvNombre);
        txvEmpleadosApellido = (TextView) findViewById(R.id.txvApellido);
        txvEmpleadosPuesto = (TextView) findViewById(R.id.txvPuesto);
        txvEmpleadosEdad = (TextView) findViewById(R.id.txvEdad);
        txvEmpleadosSalario = (TextView) findViewById(R.id.txvSalario);

        try {
            contenido = Analisis.analizarArchivoEmpleados(this);

            txvEmpleadosId.setText(contenido[0]);
            txvEmpleadosNombre.setText(contenido[1]);
            txvEmpleadosApellido.setText(contenido[2]);
            txvEmpleadosPuesto.setText(contenido[3]);
            txvEmpleadosEdad.setText(contenido[4]);
            txvEmpleadosSalario.setText(contenido[5]);
>>>>>>> origin/master

        } catch (XmlPullParserException e) {
            Snackbar.make(parentLayout, "Error: Documento XML mal formado.", Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            Snackbar.make(parentLayout, "Error: No se puede acceder al fichero XML.", Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
<<<<<<< HEAD
            Snackbar.make(parentLayout, "Error: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
=======
            Snackbar.make(parentLayout, "Error desconocido: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
>>>>>>> origin/master
        }
    }
}
