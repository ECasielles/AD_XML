package com.mercacortex.ecasielles_ad_xml.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.TextView;

import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Estacion;
import com.mercacortex.ecasielles_ad_xml.utils.Analisis;
import com.mercacortex.ecasielles_ad_xml.utils.RestClient;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;

import cz.msebera.android.httpclient.Header;

import static com.mercacortex.ecasielles_ad_xml.activity.Bizi.ESTACION_KEY;

public class EstacionActivity extends AppCompatActivity {

    public static final String N_ESTACIONES = "http://www.zaragoza.es/api/recurso/urbanismo-infraestructuras/estacion-bicicleta.xml?rf=html&results_only=false&srsname=utm30n&start=0&rows=129";
    public static final String TEMPORAL = "temporal.txt";
    private ViewGroup parentLayout;
    private Estacion estacion;
    private int nEstacion;

    private TextView txvCalle, txvEstado, txvLastUpdated, txvBicisDisponibles, txvAnclajesDisponibles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacion);
        parentLayout = (ViewGroup) findViewById(R.id.activity_estacion);
        txvCalle = (TextView) findViewById(R.id.txvCalle);
        txvEstado = (TextView) findViewById(R.id.txvStatus);
        txvLastUpdated = (TextView) findViewById(R.id.txvUpdated);
        txvBicisDisponibles = (TextView) findViewById(R.id.txvBikesAvailable);
        txvAnclajesDisponibles = (TextView) findViewById(R.id.txvPlacesAvailable);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        nEstacion = ((Estacion)data.getSerializableExtra(ESTACION_KEY)).getId();
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
                    estacion = Analisis.analizarEstacion(file, nEstacion);
                    txvCalle.setText("Estación: " + estacion.getCalle());
                    txvLastUpdated.setText("Últ. Actualización: " + estacion.getFechaUltimaModif());
                    txvEstado.setText("Estado: " + estacion.getEstado());
                    txvBicisDisponibles.setText("Bicis disponibles: " + estacion.getBicisDisponibles());
                    txvAnclajesDisponibles.setText("Anclajes disponibles: " + estacion.getAnclajesDisponibles());
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
