package com.mercacortex.ecasielles_ad_xml.repository;


import android.content.Context;

import com.mercacortex.ecasielles_ad_xml.model.Empleado;
import com.mercacortex.ecasielles_ad_xml.utils.Analisis;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoRepository {

    private static EmpleadoRepository repository;
    private ArrayList<Empleado> empleados = new ArrayList<>();

    private EmpleadoRepository(Context context) throws IOException, XmlPullParserException {
        this.empleados = Analisis.analizarArchivoEmpleados(context);
        repository = this;
    }

    public static EmpleadoRepository getInstance(Context context) throws IOException, XmlPullParserException {
        if (repository == null)
            return new EmpleadoRepository(context);
        return repository;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

}
