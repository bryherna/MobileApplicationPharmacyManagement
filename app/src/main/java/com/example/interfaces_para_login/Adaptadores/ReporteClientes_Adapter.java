package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Cliente;
import com.example.interfaces_para_login.R;

import java.util.List;

public class ReporteClientes_Adapter extends BaseAdapter {
    private Context context;
    private List<Cliente> clienteList;
    private TextView tv_apellidosNombresList,tv_fechaNacList,
            tv_dniList, tv_estadoList, tv_telefonoList,
            tv_direccionList, tv_sexoList, tv_correoList,
            tv_fechaInicio,tv_paisList;


    public ReporteClientes_Adapter(Context context, List<Cliente> clienteList) {
        this.context = context;
        this.clienteList = clienteList;
    }
    @Override
    public int getCount() {
        return clienteList.size();
    }

    @Override
    public Object getItem(int i) {
        return clienteList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.reporte_clientes_item,null);
        /*
        tv_apellidosNombresList,tv_fechaNacList,
            tv_dniList, tv_estadoList, tv_telefonoList,
            tv_direccionList, tv_sexoList, tv_correoList,
            tv_fechaInicio,tv_paisList;
         */
        tv_apellidosNombresList = (TextView) v.findViewById(R.id.tv_apellidosNombresList);
        tv_fechaNacList = (TextView) v.findViewById(R.id.tv_fechaNacList);
        tv_dniList = (TextView) v.findViewById(R.id.tv_dniList);
        tv_estadoList = (TextView) v.findViewById(R.id.tv_estadoList);
        tv_telefonoList = (TextView) v.findViewById(R.id.tv_telefonoList);
        tv_direccionList = (TextView) v.findViewById(R.id.tv_direccionList);
        tv_sexoList = (TextView) v.findViewById(R.id.tv_sexoList);
        tv_correoList = (TextView) v.findViewById(R.id.tv_correoList);
        tv_fechaInicio = (TextView) v.findViewById(R.id.tv_fechaInicio);
        tv_paisList = (TextView) v.findViewById(R.id.tv_paisList);

        //-------------------------------------------
        tv_apellidosNombresList.setText(clienteList.get(i).getPerApellidosNombres());
        tv_fechaNacList.setText(clienteList.get(i).getPerFechaNac());
        tv_dniList.setText(clienteList.get(i).getPerDni());
        if(clienteList.get(i).getPerEstado()==1){
            tv_estadoList.setText("ACTIVO");
        }else{
            tv_estadoList.setText("INACTIVO");
        }
        tv_telefonoList.setText(clienteList.get(i).getPerTelefono());
        tv_direccionList.setText(clienteList.get(i).getPerDireccion());
        if(clienteList.get(i).getPerSexo().equalsIgnoreCase("F")){
            tv_sexoList.setText("FEMENINO");
        }else{
            tv_sexoList.setText("MASCULINO");
        }
        tv_correoList.setText(clienteList.get(i).getPerCorreo());
        tv_fechaInicio.setText(clienteList.get(i).getFechaInicio());

        tv_paisList.setText(clienteList.get(i).getNomPais());



        return v;
    }
}
