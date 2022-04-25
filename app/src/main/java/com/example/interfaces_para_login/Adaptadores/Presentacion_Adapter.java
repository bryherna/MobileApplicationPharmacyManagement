package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Presentacion;
import com.example.interfaces_para_login.R;

import java.util.List;

public class Presentacion_Adapter extends BaseAdapter {
    private Context context;
    private List<Presentacion> presentacionList;
    private TextView tv_nombre_presentacion;

    public Presentacion_Adapter(Context context, List<Presentacion> presentacionList) {
        this.context = context;
        this.presentacionList = presentacionList;
    }
    @Override
    public int getCount() {
        return presentacionList.size();
    }

    @Override
    public Object getItem(int i) {
        return presentacionList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.presentacion_sp_item,null);
        tv_nombre_presentacion = (TextView)v.findViewById(R.id.tv_nombre_presentacion);
        tv_nombre_presentacion.setText(presentacionList.get(i).getNomPresentacion());

        v.setTag(presentacionList.get(i).getIdPresentacion());

        return v;

    }


}
