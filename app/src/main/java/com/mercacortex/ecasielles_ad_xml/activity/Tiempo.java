package com.mercacortex.ecasielles_ad_xml.activity;

import android.app.ProgressDialog;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.FileAsyncHttpResponseHandler;
import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Prediccion;
import com.mercacortex.ecasielles_ad_xml.utils.Analisis;
import com.mercacortex.ecasielles_ad_xml.utils.RestClient;
import com.squareup.picasso.Picasso;

import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class Tiempo extends AppCompatActivity {

    public static final String DIRECCION = "http://www.aemet.es/xml/municipios/localidad_29067.xml";
    public static final String DIRECCION_IMAGENES = "http://www.aemet.es/imagenes/png/estado_cielo/"; //imagen.png

    public static final String TEMPORAL = "tiempo.xml";
    private ViewGroup parentLayout;
    private ArrayList<Prediccion> predicciones;
    private Prediccion prediccionHoy;
    private Prediccion prediccionMañana;

    private int[] imagenesHoy = {
            R.id.imageEstadoHoy6,
            R.id.imageEstadoHoy12,
            R.id.imageEstadoHoy18,
            R.id.imageEstadoHoy24
    };
    private int[] imagenesMañana = {
            R.id.imageEstadoMañana6,
            R.id.imageEstadoMañana12,
            R.id.imageEstadoMañana18,
            R.id.imageEstadoMañana24
    };
    private int[] txvEstadoHoy = {
            R.id.txvEstadoHoy6,
            R.id.txvEstadoHoy12,
            R.id.txvEstadoHoy18,
            R.id.txvEstadoHoy24
    };
    private int[] txvEstadoMañana = {
            R.id.txvEstadoMañana6,
            R.id.txvEstadoMañana12,
            R.id.txvEstadoMañana18,
            R.id.txvEstadoMañana24
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiempo);
        parentLayout = (ViewGroup) findViewById(R.id.activity_tiempo);
        descarga(DIRECCION, TEMPORAL);
    }

    private void descarga(String direccion, String temporal) {
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
                    predicciones = Analisis.analizarPrediccionAemet(file);
                    if (predicciones != null) {
                        inicializar();
                    }
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

    private void inicializar() {
        prediccionHoy = predicciones.get(0);
        prediccionMañana = predicciones.get(1);

        TextView txvFechaHoy, txvFechaMañana;
        txvFechaHoy = (TextView) findViewById(R.id.txvFechaHoy);
        txvFechaMañana = (TextView) findViewById(R.id.txvFechaMañana);
        txvFechaHoy.setText(prediccionHoy.getDay());
        txvFechaMañana.setText(prediccionMañana.getDay());

        asignaImagenes(prediccionHoy, imagenesHoy, txvEstadoHoy);
        asignaImagenes(prediccionMañana, imagenesMañana, txvEstadoMañana);

        TextView txvTempHoy, txvTempMañana;
        txvTempHoy = (TextView) findViewById(R.id.txvMinMaxHoy);
        txvTempMañana = (TextView) findViewById(R.id.txvMinMaxMañana);
        txvTempHoy.setText(prediccionHoy.getTempMin() + "/" + prediccionHoy.getTempMax());
        txvTempMañana.setText(prediccionMañana.getTempMin() + "/" + prediccionMañana.getTempMax());
    }

    private void asignaImagenes(Prediccion prediccion, int[] contenedoresImagen, int[] contenedoresTexto) {
        ImageView imgView = null;
        TextView txvPeriodo = null;
        String codImagen;
        for (Prediccion.EstadoCielo estado : prediccion.getEstadosCielo()) {
            switch (estado.getPeriodo()) {
                case "00-06":
                    imgView = (ImageView) findViewById(contenedoresImagen[0]);
                    txvPeriodo = (TextView)findViewById(contenedoresTexto[0]);
                    break;
                case "06-12":
                    imgView = (ImageView) findViewById(contenedoresImagen[1]);
                    txvPeriodo = (TextView)findViewById(contenedoresTexto[1]);
                    break;
                case "12-18":
                    imgView = (ImageView) findViewById(contenedoresImagen[2]);
                    txvPeriodo = (TextView)findViewById(contenedoresTexto[2]);
                    break;
                case "18-24":
                    imgView = (ImageView) findViewById(contenedoresImagen[3]);
                    txvPeriodo = (TextView)findViewById(contenedoresTexto[3]);
                    break;
            }
            imgView.setVisibility(View.VISIBLE);
            codImagen = estado.getCodImagen();
            txvPeriodo.setText(estado.getPeriodo());
            txvPeriodo.setVisibility(View.VISIBLE);
            Picasso.with(this)
                    .load(DIRECCION_IMAGENES + codImagen + "_g.png").error(R.drawable.error_soleado)
                    .into(imgView);
        }

    }

}
