package com.mercacortex.ecasielles_ad_xml.activity;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Estacion;
import com.mercacortex.ecasielles_ad_xml.utils.Analisis;
import com.mercacortex.ecasielles_ad_xml.utils.RestClient;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Bizi extends AppCompatActivity {

    public static final String N_ESTACIONES = "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/estacion-bicicleta.xml?rf=html&results_only=false&srsname=utm30n&start=0&rows=129";
    public static final String TEMPORAL= "temporal.txt";
    private ViewGroup parentLayout;
    private ArrayList<Estacion> estaciones;

    private boolean aux = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bizi);
        parentLayout = (ViewGroup) findViewById(R.id.activity_bizi);
        descarga(N_ESTACIONES, TEMPORAL);
    }


    private void descarga(final String direccion, String temporal) {
        final ProgressDialog progreso = new ProgressDialog(this);
        File miFichero = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), temporal);
        RestClient.get(direccion, new FileAsyncHttpResponseHandler(miFichero) {
            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {
                progreso.dismiss();
                Snackbar.make(parentLayout, "Error: " + throwable.getLocalizedMessage(), Snackbar.LENGTH_LONG).show();
            }
            @Override
            public void onSuccess(int statusCode, Header[] headers, File file) {
                progreso.dismiss();
                Snackbar.make(parentLayout, "Fichero descargado OK\n" + file.getPath(), Snackbar.LENGTH_LONG).show();
                try {
                    estaciones = Analisis.analizarEstacionesBizi(file);
                    Snackbar.make(parentLayout, estaciones.get(1).getCalle(), Snackbar.LENGTH_LONG).show();
                } catch (XmlPullParserException e) {
                    Snackbar.make(parentLayout, "Excepcion XML: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                } catch (IOException e) {
                    Snackbar.make(parentLayout, "Excepcion I/O: " + e.getMessage(), Snackbar.LENGTH_LONG).show();
                }
            }
            @Override
            public void onStart() {
                super.onStart();
                progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progreso.setMessage("Conectando . . .");
                progreso.setCancelable(false);
                progreso.show();
            }
        });
    }
}
