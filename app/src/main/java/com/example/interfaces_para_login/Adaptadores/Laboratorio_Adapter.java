package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Laboratorio;
import com.example.interfaces_para_login.Entidades.Presentacion;
import com.example.interfaces_para_login.R;

import java.util.List;

public class Laboratorio_Adapter extends BaseAdapter {
    private Context context;
    private List<Laboratorio> laboratorioList;
    private TextView tv_nomLaboratorio;

    public Laboratorio_Adapter(Context context, List<Laboratorio> laboratorioList) {
        this.context = context;
        this.laboratorioList = laboratorioList;
    }
    @Override
    public int getCount() {
        return laboratorioList.size();
    }

    @Override
    public Object getItem(int i) {
        return laboratorioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.laboratorio_sp_item,null);
        tv_nomLaboratorio = (TextView)v.findViewById(R.id.tv_nomLaboratorio);
        tv_nomLaboratorio.setText(laboratorioList.get(i).getNomLaboratorio());

        v.setTag(laboratorioList.get(i).getIdLaboratorio());

        return v;

    }

}
