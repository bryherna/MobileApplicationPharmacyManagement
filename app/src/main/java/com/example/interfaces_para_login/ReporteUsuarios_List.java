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
import com.example.interfaces_para_login.Adaptadores.ReporteUsuarios_Adapter;
import com.example.interfaces_para_login.Entidades.Conexion;
import com.example.interfaces_para_login.Entidades.Producto;
import com.example.interfaces_para_login.Entidades.Usuario;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ReporteUsuarios_List extends AppCompatActivity {
    private ListView listado_reporteUsuarios;
    private RequestQueue requestQueue;
    private String url;
    private ReporteUsuarios_Adapter reporteUsuarios_adapter;
    private List<Usuario> usuarioList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte_usuarios_list);

        listado_reporteUsuarios = (ListView) findViewById(R.id.listado_reporteUsuarios);

        url = Conexion.URL + "ws_usuarios.php?listar";
        listarReporteUsuarios(url);
        setTitle("REPORTE LISTADO DE USUARIOS");

        listado_reporteUsuarios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent nuevo = new Intent(ReporteUsuarios_List.this,ModificarUsuario.class);
                nuevo.putExtra("idUsuario",view.getTag().toString());
                startActivity(nuevo);
            }
        });

    }
    private void listarReporteUsuarios(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
                usuarioList = new ArrayList<>();
                try{
                    for(int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        usuarioList.add(new Usuario(jsonObject.getInt("idUsuario"),
                                jsonObject.getString("nombreUsuario"),jsonObject.getString("nombreTipoUsuario"),
                                jsonObject.getString("perApellidosNombres"),
                                jsonObject.getString("password_usuario"),
                                jsonObject.getInt("estadoUsuario")));

                    }
                    reporteUsuarios_adapter = new ReporteUsuarios_Adapter(ReporteUsuarios_List.this,usuarioList);
                    listado_reporteUsuarios.setAdapter(reporteUsuarios_adapter);

                }catch(JSONException e){
                    Toast.makeText(ReporteUsuarios_List.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ReporteUsuarios_List.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}