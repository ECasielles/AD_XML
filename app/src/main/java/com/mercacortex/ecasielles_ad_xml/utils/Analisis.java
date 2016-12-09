package com.mercacortex.ecasielles_ad_xml.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.os.Environment;
import android.util.Xml;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Empleado;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;


public class Analisis {

    public static String analizar(String texto) throws XmlPullParserException, IOException {
        StringBuilder cadena = new StringBuilder();
        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new StringReader(texto));
        int eventType = xpp.getEventType();
        cadena.append("Inicio . . . \n");
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT) {
                //System.out.println("Start document");
                cadena.append("Start document" + "\n");
            } else if (eventType == XmlPullParser.START_TAG) {
                //stem.out.println("Start tag "+xpp.getName());
                cadena.append("Start tag " + xpp.getName() + "\n");
            } else if (eventType == XmlPullParser.END_TAG) {
                //System.out.println("End tag "+xpp.getName());
                cadena.append("End tag " + xpp.getName() + "\n");
            } else if (eventType == XmlPullParser.TEXT) {
                //System.out.println("Text "+xpp.getText());
                cadena.append("Text " + xpp.getText() + "\n");
            }
            eventType = xpp.next();
        }
        //System.out.println("End document");
        cadena.append("End document" + "\n" + "Fin");
        return cadena.toString();
    }
    public static String[] analizarArchivoEmpleados(Context c) throws XmlPullParserException, IOException, NumberFormatException {

        StringBuilder cadena = new StringBuilder();
        Empleado unEmpleado;

        Double
                mediaEdad = 0.0d,
                mediaSalario = 0.0d,
                maxSalario = 0.0d,
                minSalario = 0.0d;

        int contador = 0;

        XmlResourceParser xrp = c.getResources().getXml(R.xml.empleados);
        int eventType = xrp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            unEmpleado = new Empleado();
            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if(xrp.getName().equals("nombre"))

                    if(xrp.getName().equals("nota")) {
                        for (int i = 0; i < xrp.getAttributeCount(); i++)
                            cadena.append(xrp.getAttributeName(i) + ":" + xrp.getAttributeValue(i) + "\n");
                    }
                    break;
                case XmlPullParser.TEXT:
                    // Añadido en clase
                    if (esNombre)
                        unEmpleado.setId(Integer.parseInt(xrp.getText());
                        unEmpleado.setNombre(xrp.getText());
                        unEmpleado.setApellido(xrp.getText());
                        unEmpleado.setPuesto(xrp.getText());
                        unEmpleado.setEdad(Integer.parseInt(xrp.getText());
                        unEmpleado.setSalario(Float.parseFloat(xrp.getText()));

                    if (esNota) {
                        cadena.append("Nota: " + xrp.getText() + "\n");
                        mediaEdad += Double.parseDouble(xrp.getText());
                        contador++;
                    }
                    break;
                case XmlPullParser.END_TAG:
                    // Añadido en clase
                    if(xrp.getName().equals("nombre"))
                        esNombre = false;
                    if(xrp.getName().equals("nota"))
                        esNota = false;
                    break;
            }
            eventType = xrp.next();
        }
        // Añadido en clase
        if (contador > 0)
            cadena.append("Nota mediaEdad: " + String.format("%2f", mediaEdad / contador));
        xrp.close();

        return cadena.toString();
    }
}


