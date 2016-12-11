package com.mercacortex.ecasielles_ad_xml.utils;

import android.content.Context;
import android.content.res.XmlResourceParser;
import android.util.Xml;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Empleado;
import com.mercacortex.ecasielles_ad_xml.model.Prediccion;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


public class Analisis {

    public static ArrayList<Empleado> analizarArchivoEmpleados(Context c) throws XmlPullParserException, IOException, NumberFormatException {

        ArrayList<Empleado> empleadoArrayList = new ArrayList<>();
        Empleado unEmpleado = new Empleado();

        XmlResourceParser xrp = c.getResources().getXml(R.xml.empleados);
        int eventType = xrp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG) {
                if(xrp.getName().equals("empleado"))
                    unEmpleado = new Empleado();
                if(xrp.getName().equals("id"))
                       unEmpleado.setId(Integer.parseInt(xrp.nextText()));
                if(xrp.getName().equals("nombre"))
                        unEmpleado.setNombre(xrp.nextText());
                if(xrp.getName().equals("apellido"))
                        unEmpleado.setApellido(xrp.nextText());
                if(xrp.getName().equals("puesto"))
                        unEmpleado.setPuesto(xrp.nextText());
                if(xrp.getName().equals("edad"))
                        unEmpleado.setEdad(Integer.parseInt(xrp.nextText()));
                if(xrp.getName().equals("salario"))
                        unEmpleado.setSalario(Float.parseFloat(xrp.nextText()));
            }
            if(eventType == XmlPullParser.END_TAG)
                if(xrp.getName().equals("empleado"))
                    empleadoArrayList.add(unEmpleado);
            eventType = xrp.next();
        }

        return empleadoArrayList;
    }

    public static ArrayList<Prediccion> analizarPrediccionAemet(File file) throws XmlPullParserException, IOException, NumberFormatException {
        ArrayList<Prediccion> predicciones = new ArrayList<>();
        Prediccion prediccion = null;

        XmlPullParser xpp = Xml.newPullParser();
        xpp.setInput(new FileReader(file));
        int eventType = xpp.getEventType();
        boolean leerTemperatura = false;

        while (eventType != XmlPullParser.END_DOCUMENT && predicciones.size() < 2) {
            if(eventType == XmlPullParser.START_TAG) {
                if (xpp.getName().equals("dia")) {
                    prediccion = new Prediccion();
                    prediccion.setDay(xpp.getAttributeValue(0));
                }
                //Comprueba que tenga nombre correcto, 2 atributos y texto no vacíos
                if (xpp.getName().equals("estado_cielo") && xpp.getAttributeCount() > 1 && !xpp.isEmptyElementTag()) {
                    if(!xpp.getAttributeValue(1).equals("") && (xpp.getAttributeValue(0).equals("00-06") ||
                            xpp.getAttributeValue(0).equals("06-12") ||
                            xpp.getAttributeValue(0).equals("12-18") ||
                            xpp.getAttributeValue(0).equals("18-24")))
                        prediccion.addEstadoCielo(
                                xpp.getAttributeValue(0),
                                xpp.getAttributeValue(1),
                                xpp.nextText()
                        );
                    }
                if (xpp.getName().equals("temperatura"))
                    leerTemperatura = true;
                if (xpp.getName().equals("maxima") && leerTemperatura)
                    prediccion.setTempMax(xpp.nextText());
                if (xpp.getName().equals("minima") && leerTemperatura)
                    prediccion.setTempMin(xpp.nextText());
            } else if(eventType == XmlPullParser.END_TAG) {
                if(xpp.getName().equals("dia"))
                     predicciones.add(prediccion);
                else if (xpp.getName().equals("temperatura"))
                    leerTemperatura = false;
            }
            eventType = xpp.next();
        }
        return predicciones;
    }
}


