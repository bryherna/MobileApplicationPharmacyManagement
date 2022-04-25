package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.interfaces_para_login.Adaptadores.ReporteProductos_Adapter;
import com.example.interfaces_para_login.Entidades.Conexion;
import com.example.interfaces_para_login.Entidades.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReporteProductos_List extends AppCompatActivity {
    private ListView listado_reporteProductos;
    private RequestQueue requestQueue;
    private String url;
    private ReporteProductos_Adapter reporteProductos_adapter;
    private List<Producto> productoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_productos_list);

        listado_reporteProductos = (ListView) findViewById(R.id.listado_reporteProductos);

        url = Conexion.URL+"ws_producto.php?reporte_productos";
        listarReporteProducto(url);
        setTitle("REPORTE LISTADO DE PRODUCTOS");

        listado_reporteProductos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nuevo = new Intent(ReporteProductos_List.this,ModificarProducto.class);
                nuevo.putExtra("producto_id",view.getTag().toString());
                startActivity(nuevo);
            }
        });
    }
    private void listarReporteProducto(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
                productoList = new ArrayList<>();
                try{
                    for(int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        productoList.add(new Producto(jsonObject.getInt("idProducto"),jsonObject.getString("nomPresentacion"),
                                jsonObject.getString("nomLaboratorio"),jsonObject.getString("nomProducto"),
                                jsonObject.getDouble("precioVenta"),jsonObject.getString("descripcion"),
                                jsonObject.getInt("stock"),jsonObject.getString("caducidad"),
                                jsonObject.getString("lote")));

                    }
                    reporteProductos_adapter = new ReporteProductos_Adapter(ReporteProductos_List.this,productoList);
                    listado_reporteProductos.setAdapter(reporteProductos_adapter);

                }catch(JSONException e){
                    Toast.makeText(ReporteProductos_List.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReporteProductos_List.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}