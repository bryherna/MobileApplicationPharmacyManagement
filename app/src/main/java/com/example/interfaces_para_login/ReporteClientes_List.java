package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.interfaces_para_login.Adaptadores.ReporteClientes_Adapter;
import com.example.interfaces_para_login.Adaptadores.ReporteProductos_Adapter;
import com.example.interfaces_para_login.Entidades.Cliente;
import com.example.interfaces_para_login.Entidades.Conexion;
import com.example.interfaces_para_login.Entidades.Producto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReporteClientes_List extends AppCompatActivity {
    private ListView listado_reporteClientes;
    private RequestQueue requestQueue;
    private String url;
    private ReporteClientes_Adapter reporteClientes_adapter;
    private List<Cliente> clienteList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_clientes_list);

        listado_reporteClientes = (ListView) findViewById(R.id.listado_reporteClientes);
        setTitle("REPORTE LISTADO DE CLIENTES");

        url = Conexion.URL + "ws_cliente.php?listaClientesGeneral";
        listarReporteCliente(url);
    }

    private void listarReporteCliente(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
                clienteList = new ArrayList<>();
                try{
                    for(int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        clienteList.add(new Cliente(jsonObject.getInt("idPersona"),
                                jsonObject.getString("nomPais"),jsonObject.getString("perApellidosNombres"),
                                jsonObject.getString("perFechaNac"),jsonObject.getString("perDni"),
                                jsonObject.getInt("perEstado"),jsonObject.getString("perTelefono"),
                                jsonObject.getString("perDireccion"),jsonObject.getString("perSexo"),
                                jsonObject.getString("perCorreo"),jsonObject.getInt("idCliente"),
                                jsonObject.getString("fechaInicio")));



                    }
                    reporteClientes_adapter = new ReporteClientes_Adapter(ReporteClientes_List.this,clienteList);
                    listado_reporteClientes.setAdapter(reporteClientes_adapter);

                }catch(JSONException e){
                    Toast.makeText(ReporteClientes_List.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReporteClientes_List.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}