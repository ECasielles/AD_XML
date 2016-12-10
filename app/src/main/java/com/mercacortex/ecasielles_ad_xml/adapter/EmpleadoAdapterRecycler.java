package com.mercacortex.ecasielles_ad_xml.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Empleado;
import com.mercacortex.ecasielles_ad_xml.repository.EmpleadoRepository;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;


public class EmpleadoAdapterRecycler extends RecyclerView.Adapter<EmpleadoAdapterRecycler.EmpleadoViewHolder> {

    private List<Empleado> empleados;
    private Context context;

    public EmpleadoAdapterRecycler(Context context) throws IOException, XmlPullParserException {
        this.context = context;
        empleados = EmpleadoRepository.getInstance(context).getEmpleados();
    }

    @Override
    public EmpleadoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empleado, parent, false);
        return new EmpleadoViewHolder(item);
    }

    @Override
    public void onBindViewHolder(EmpleadoViewHolder itemHolder, int position) {
        itemHolder.txvEmpleadosId.setText("Id de Empleado: " + String.valueOf(empleados.get(position).getId()));
        itemHolder.txvEmpleadosNombre.setText("Nombre: " + empleados.get(position).getNombre());
        itemHolder.txvEmpleadosApellido.setText("Apellido: " + empleados.get(position).getApellido());
        itemHolder.txvEmpleadosPuesto.setText("Puesto: " + empleados.get(position).getPuesto());
        itemHolder.txvEmpleadosEdad.setText("Edad: " + empleados.get(position).getEdad());
        itemHolder.txvEmpleadosSalario.setText("Salario: " + String.valueOf(empleados.get(position).getSalario()));
    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }


    public float mediaEdad() throws Exception {
        float sumaEdad = 0f;
        int nEmpleados = getItemCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                sumaEdad += empleados.get(i).getEdad();
            sumaEdad /= nEmpleados;
            return sumaEdad;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float maxEdad() throws Exception {
        int maxEdad = Integer.MIN_VALUE;
        int nEmpleados = getItemCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                maxEdad = empleados.get(i).getEdad() > maxEdad ? empleados.get(i).getEdad() : maxEdad;
            return maxEdad;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float minEdad() throws Exception {
        int minEdad = Integer.MAX_VALUE;
        int nEmpleados = getItemCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                minEdad = empleados.get(i).getEdad() < minEdad ? empleados.get(i).getEdad() : minEdad;
            return minEdad;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float mediaSalario() throws Exception {
        float sumaSalario = 0f;
        int nEmpleados = getItemCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                sumaSalario += empleados.get(i).getSalario();
            sumaSalario /= nEmpleados;
            return sumaSalario;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float maxSalario() throws Exception {
        float maxSalario = Float.MIN_VALUE;
        int nEmpleados = getItemCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                maxSalario = empleados.get(i).getSalario() > maxSalario ? empleados.get(i).getSalario() : maxSalario;
            return maxSalario;
        }
        else
            throw new Exception("Error: No hay elementos");
    }
    public float minSalario() throws Exception {
        float minSalario = Float.MAX_VALUE;
        int nEmpleados = getItemCount();
        if (nEmpleados > 0) {
            for (int i = 0; i < nEmpleados; i++)
                minSalario = empleados.get(i).getSalario() < minSalario ? empleados.get(i).getSalario() : minSalario;
            return minSalario;
        }
        else
            throw new Exception("Error: No hay elementos");
    }

    public static class EmpleadoViewHolder extends RecyclerView.ViewHolder{
        TextView txvEmpleadosId;
        TextView txvEmpleadosNombre;
        TextView txvEmpleadosApellido;
        TextView txvEmpleadosPuesto;
        TextView txvEmpleadosEdad;
        TextView txvEmpleadosSalario;

        public EmpleadoViewHolder(View view) {
            super(view);
            txvEmpleadosId = (TextView) view.findViewById(R.id.txvEmpleadoId);
            txvEmpleadosNombre = (TextView) view.findViewById(R.id.txvEmpleadoNombre);
            txvEmpleadosApellido = (TextView) view.findViewById(R.id.txvEmpleadoApellido);
            txvEmpleadosPuesto = (TextView) view.findViewById(R.id.txvEmpleadoPuesto);
            txvEmpleadosEdad = (TextView) view.findViewById(R.id.txvEmpleadoEdad);
            txvEmpleadosSalario = (TextView) view.findViewById(R.id.txvEmpleadoSalario);
        }
    }

}
