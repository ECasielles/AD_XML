package com.mercacortex.ecasielles_ad_xml.adapter;


import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mercacortex.ecasielles_ad_xml.R;
import com.mercacortex.ecasielles_ad_xml.model.Estacion;

import java.util.ArrayList;

public class EstacionAdapter extends ArrayAdapter<Estacion>{

    Context context;

    public EstacionAdapter(Context context, int resource, ArrayList<Estacion> estaciones) {
        super(context, resource, estaciones);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        EstacionHolder holder;

        if(view == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            view = inflater.inflate(R.layout.item_estacion, null);

            holder = new EstacionHolder();
            holder.textView = (TextView) view.findViewById(R.id.txvEstacion);
            holder.layout = (RelativeLayout) view.findViewById(R.id.activity_bizi);
            view.setTag(holder);
        } else {
            holder = (EstacionHolder) view.getTag();
        }

        holder.textView.setText(getItem(position).getCalle());
        holder.textView.setTextColor(context.getResources().getColor(R.color.colorTexto));
        holder.layout.setBackgroundResource(R.color.colorPrimaryDark);

        return view;
    }

    class EstacionHolder {
        TextView textView;
        ViewGroup layout;
    }
}
