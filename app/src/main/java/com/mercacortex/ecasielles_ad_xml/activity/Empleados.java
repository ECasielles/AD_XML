package com.mercacortex.ecasielles_ad_xml.activity;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Empleado;
import com.mercacortex.ecasielles_ad_xml.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;

public class Empleados extends AppCompatActivity {

    TextView txvEmpleadosId;
    TextView txvEmpleadosNombre;
    TextView txvEmpleadosApellido;
    TextView txvEmpleadosPuesto;
    TextView txvEmpleadosEdad;
    TextView txvEmpleadosSalario;

    ViewGroup parentLayout;

    Empleado[] Empleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleados);

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

        } catch (XmlPullParserException e) {
            Snackbar.make(parentLayout, "Error: Documento XML mal formado.", Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            Snackbar.make(parentLayout, "Error: No se puede acceder al fichero XML.", Snackbar.LENGTH_LONG).show();
        } catch (Exception e) {
            Snackbar.make(parentLayout, "Error desconocido: " + e.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
        }
    }
}
