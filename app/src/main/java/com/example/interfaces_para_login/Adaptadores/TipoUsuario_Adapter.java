package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Laboratorio;
import com.example.interfaces_para_login.Entidades.TipoUsuario;
import com.example.interfaces_para_login.R;

import java.util.List;

public class TipoUsuario_Adapter extends BaseAdapter {
    private Context context;
    private List<TipoUsuario> tipoUsuarioList;
    private TextView tv_tipo_usuario_sp;

    public TipoUsuario_Adapter(Context context, List<TipoUsuario> tipoUsuarioList) {
        this.context = context;
        this.tipoUsuarioList = tipoUsuarioList;
    }
    @Override
    public int getCount() {
        return tipoUsuarioList.size();
    }

    @Override
    public Object getItem(int i) {
        return tipoUsuarioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.tipo_usuario_sp_item,null);

        tv_tipo_usuario_sp = (TextView) v.findViewById(R.id.tv_tipo_usuario_sp);

        tv_tipo_usuario_sp.setText(tipoUsuarioList.get(i).getNombreTipoUsuario());

        v.setTag(tipoUsuarioList.get(i).getIdTipoUsuario());




        return v;
    }
}
