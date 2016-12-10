package com.mercacortex.ecasielles_ad_xml.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Empleado;
import com.mercacortex.ecasielles_ad_xml.repository.EmpleadoRepository;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;


public class EmpleadoAdapter extends ArrayAdapter<Empleado> {

    public EmpleadoAdapter(Context context) throws IOException, XmlPullParserException {
        super(context, R.layout.item_empleado, EmpleadoRepository.getInstance(context).getEmpleados());
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        LayoutInflater inflater;
        EmpleadoHolder itemHolder;

        if(view == null) {

            inflater = LayoutInflater.from(getContext());

            view = inflater.inflate(R.layout.item_empleado, null);

            itemHolder = new EmpleadoHolder();

            itemHolder.txvEmpleadosId = (TextView) view.findViewById(R.id.txvId);
            itemHolder.txvEmpleadosNombre = (TextView) view.findViewById(R.id.txvNombre);
            itemHolder.txvEmpleadosApellido = (TextView) view.findViewById(R.id.txvApellido);
            itemHolder.txvEmpleadosPuesto = (TextView) view.findViewById(R.id.txvPuesto);
            itemHolder.txvEmpleadosEdad = (TextView) view.findViewById(R.id.txvEdad);
            itemHolder.txvEmpleadosSalario = (TextView) view.findViewById(R.id.txvSalario);

            view.setTag(itemHolder);

        } else {
            itemHolder = (EmpleadoHolder) view.getTag();
        }

        itemHolder.txvEmpleadosId.setText(getItem(position).getId());
        itemHolder.txvEmpleadosNombre.setText(getItem(position).getNombre());
        itemHolder.txvEmpleadosApellido.setText(getItem(position).getApellido());
        itemHolder.txvEmpleadosPuesto.setText(getItem(position).getPuesto());
        itemHolder.txvEmpleadosEdad.setText(getItem(position).getEdad());
        itemHolder.txvEmpleadosSalario.setText(String.valueOf(getItem(position).getSalario()));

        return view;
    }

    class EmpleadoHolder {
        TextView txvEmpleadosId;
        TextView txvEmpleadosNombre;
        TextView txvEmpleadosApellido;
        TextView txvEmpleadosPuesto;
        TextView txvEmpleadosEdad;
        TextView txvEmpleadosSalario;
    }

    public float mediaEdad() throws Exception {
        float sumaEdad = 0f;
        int nEmpleados = getCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                sumaEdad += getItem(i).getEdad();
            sumaEdad /= nEmpleados;
            return sumaEdad;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float mediaSalario() throws Exception {
        float sumaSalario = 0f;
        int nEmpleados = getCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                sumaSalario += getItem(i).getSalario();
            sumaSalario /= nEmpleados;
            return sumaSalario;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float maxSalario() throws Exception {
        float maxSalario = 0;
        int nEmpleados = getCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                maxSalario = getItem(i).getEdad() > maxSalario ? getItem(i).getEdad() : maxSalario;
            return maxSalario;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float minSalario() throws Exception {
        float minSalario = 0;
        int nEmpleados = getCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                minSalario = getItem(i).getEdad() < minSalario ? getItem(i).getEdad() : minSalario;
            return minSalario;
        }
        else
            throw new Exception("Error: No hay elementos");
    }

}
