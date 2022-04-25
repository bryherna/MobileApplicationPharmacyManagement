package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Trabajador;
import com.example.interfaces_para_login.R;

import java.util.List;

public class trabajador_Adapter  extends BaseAdapter {
    private Context context;
    private List<Trabajador> trabajadorList;
    private TextView tv_dni_trabajador_sp;

    public trabajador_Adapter(Context context, List<Trabajador> trabajadorList) {
        this.context = context;
        this.trabajadorList = trabajadorList;
    }
    @Override
    public int getCount() {
        return trabajadorList.size();
    }

    @Override
    public Object getItem(int i) {
        return trabajadorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.dni_trabajador_sp_item,null);
        tv_dni_trabajador_sp = (TextView) v.findViewById(R.id.tv_dni_trabajador_sp);
        tv_dni_trabajador_sp.setText(trabajadorList.get(i).getPerDni());

        v.setTag(trabajadorList.get(i).getIdTrabajador());

        return v;
    }
}
