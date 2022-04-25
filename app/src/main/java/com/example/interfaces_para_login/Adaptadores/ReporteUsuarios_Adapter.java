package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Usuario;
import com.example.interfaces_para_login.R;

import java.util.List;

public class ReporteUsuarios_Adapter extends BaseAdapter {
    private Context context;
    private List<Usuario> usuarioList;
    private TextView tv_nomUsuarioList,tv_nomTipoUsuarioList,tv_nomApelliList,tv_estadoUsuList;

    public ReporteUsuarios_Adapter(Context context, List<Usuario> usuarioList) {
        this.context = context;
        this.usuarioList = usuarioList;
    }
    @Override
    public int getCount() {
        return usuarioList.size();
    }

    @Override
    public Object getItem(int i) {
        return usuarioList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.reporte_usuarios_item,null);
        //private TextView tv_nomUsuarioList,tv_nomTipoUsuarioList,tv_nomApelliList,tv_estadoUsuList;

        tv_nomUsuarioList = (TextView) v.findViewById(R.id.tv_nomUsuarioList);
        tv_nomTipoUsuarioList = (TextView) v.findViewById(R.id.tv_nomTipoUsuarioList);
        tv_nomApelliList = (TextView) v.findViewById(R.id.tv_nomApelliList);
        tv_estadoUsuList = (TextView) v.findViewById(R.id.tv_estadoUsuList);

        //-------------------------------------------------------------------------

        tv_nomUsuarioList.setText(usuarioList.get(i).getNombreUsuario());
        tv_nomTipoUsuarioList.setText(usuarioList.get(i).getNombreTipoUsuario());
        tv_nomApelliList.setText(usuarioList.get(i).getPerApellidosNombres());
        if(usuarioList.get(i).getEstadoUsuario() == 1){
            tv_estadoUsuList.setText("ACTIVO");
        }else{
            tv_estadoUsuList.setText("INACTIVO");
        }
        v.setTag(usuarioList.get(i).getIdUsuario());

        return v;
    }
}
