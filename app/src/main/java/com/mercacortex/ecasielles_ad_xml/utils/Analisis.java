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

    public static ArrayList<Empleado> analizarArchivoEmpleados(Context c) throws XmlPullParserException, IOException, NumberFormatException {

        ArrayList<Empleado> empleadoArrayList = new ArrayList<>();
        Empleado unEmpleado = new Empleado();

        XmlResourceParser xrp = c.getResources().getXml(R.xml.empleados);
        int eventType = xrp.getEventType();

        while (eventType != XmlPullParser.END_DOCUMENT) {
            if(eventType == XmlPullParser.START_TAG) {
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
                    if(xrp.getName().equals("salario")){
                        unEmpleado.setSalario(Float.parseFloat(xrp.nextText()));
                        empleadoArrayList.add(unEmpleado);
                    }
            }
            eventType = xrp.next();
        }

        return empleadoArrayList;
    }
}


