package com.example.interfaces_para_login.Adaptadores;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.interfaces_para_login.Entidades.Producto;
import com.example.interfaces_para_login.R;

import org.w3c.dom.Text;

import java.util.List;

public class ReporteProductos_Adapter extends BaseAdapter {
    private Context context;
    private List<Producto> productoList;
    private TextView tv_idProducto,tv_nomPresentacionList,tv_nomLaboratorioList,tv_nomProductoList,
            tv_precioList,tv_descripcionList,tv_stockList,tv_caducidadList,tv_loteList;

    public ReporteProductos_Adapter(Context context, List<Producto> productoList) {
        this.context = context;
        this.productoList = productoList;
    }
    @Override
    public int getCount() {
        return productoList.size();
    }

    @Override
    public Object getItem(int i) {
        return productoList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View v = View.inflate(context, R.layout.reporte_productos_item,null);

       /*
       private TextView tv_idProducto,tv_nomPresentacionLis,tv_nomLaboratorioList,tv_nomProductoList,
            tv_precioList,tv_descripcionList,tv_stockList,tv_caducidadList,tv_loteList;
        */
        tv_idProducto = (TextView) v.findViewById(R.id.tv_idProducto);
        tv_nomPresentacionList = (TextView) v.findViewById(R.id.tv_nomPresentacionList);
        tv_nomLaboratorioList = (TextView) v.findViewById(R.id.tv_nomLaboratorioList);
        tv_nomProductoList = (TextView) v.findViewById(R.id.tv_nomProductoList);
        tv_precioList = (TextView) v.findViewById(R.id.tv_precioList);
        tv_descripcionList = (TextView) v.findViewById(R.id.tv_descripcionList);
        tv_stockList = (TextView) v.findViewById(R.id.tv_stockList);
        tv_caducidadList = (TextView) v.findViewById(R.id.tv_caducidadList);
        tv_loteList = (TextView) v.findViewById(R.id.tv_loteList);

        //------------------------------------------------
        tv_idProducto.setText(String.valueOf(productoList.get(i).getIdProducto()));
        tv_nomPresentacionList.setText(productoList.get(i).getNomPresentacion());
        tv_nomLaboratorioList.setText(productoList.get(i).getNomLaboratorio());
        tv_nomProductoList.setText(productoList.get(i).getNomProducto());
        tv_precioList.setText(String.valueOf(productoList.get(i).getPrecioVenta()));
        tv_descripcionList.setText(productoList.get(i).getDescripcion());
        tv_stockList.setText(String.valueOf(productoList.get(i).getStock()));
        tv_caducidadList.setText(productoList.get(i).getCaducidad());
        tv_loteList.setText(productoList.get(i).getLote());

        v.setTag(productoList.get(i).getIdProducto());

        return v;


    }
}
