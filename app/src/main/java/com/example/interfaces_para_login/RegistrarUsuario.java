package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.interfaces_para_login.Adaptadores.Laboratorio_Adapter;
import com.example.interfaces_para_login.Adaptadores.TipoUsuario_Adapter;
import com.example.interfaces_para_login.Adaptadores.trabajador_Adapter;
import com.example.interfaces_para_login.Entidades.Conexion;
import com.example.interfaces_para_login.Entidades.Laboratorio;
import com.example.interfaces_para_login.Entidades.TipoUsuario;
import com.example.interfaces_para_login.Entidades.Trabajador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistrarUsuario extends AppCompatActivity {
    private Spinner sp_tipoUsuario, sp_dniTrabajador;
    private String urlTipoUsuario, urlDniTrabajador;
    private RequestQueue requestQueue;

    private List<TipoUsuario> tipoUsuarioList;
    private TipoUsuario_Adapter tipoUsuario_adapter;

    private List<Trabajador> trabajadorList;
    private trabajador_Adapter trabajador_adapter;

    //botones
    private Button btnRegistrarUsuario;

    //text edit
    private EditText edt_nombreUsuario,edt_pass_usuario;
    private String idTipoUsuario;
    private String idTrabajador;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_usuario);

        sp_tipoUsuario = (Spinner) findViewById(R.id.sp_tipoUsuario);
        sp_dniTrabajador = (Spinner) findViewById(R.id.sp_dniTrabajador);
        btnRegistrarUsuario = (Button)findViewById(R.id.btnRegistrarUsuario);

        //inicializar edit text

        edt_nombreUsuario = (EditText) findViewById(R.id.edt_nombreUsuario);
        edt_pass_usuario = (EditText) findViewById(R.id.edt_pass_usuario);

        setTitle("REGISTRAR USUARIO");

        urlTipoUsuario = Conexion.URL+"ws_tipoUsuarios.php?listar";
        listadoTipoUsuario(urlTipoUsuario);
        urlDniTrabajador = Conexion.URL+"ws_trabajadores.php?listar";
        listadoDniTrabajador(urlDniTrabajador);

        idTipoUsuario ="";
        idTrabajador="";


        btnRegistrarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarUsuario();
            }
        });
        sp_tipoUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTipoUsuario = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_dniTrabajador.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTrabajador = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }
    private void registrarUsuario(){
        String url = Conexion.URL+"ws_usuarios.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegistrarUsuario.this,"SE REGISTRO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                Intent principal = new Intent(RegistrarUsuario.this,MenuGestionUsuarios.class);
                startActivity(principal);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarUsuario.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String,String>();
                //parametros.put("idProducto", String.valueOf(0) );
                parametros.put("nombreUsuario", edt_nombreUsuario.getText().toString() );
                parametros.put("idTipoUsuario", idTipoUsuario );
                parametros.put("idTrabajador",  idTrabajador );
                parametros.put("password_usuario",edt_pass_usuario.getText().toString());
                parametros.put("estadoUsuario",String.valueOf(1));

                parametros.put("insertar","");

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(RegistrarUsuario.this);
        requestQueue.add(stringRequest);


    }

    private void listadoTipoUsuario(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                tipoUsuarioList = new ArrayList<>();
                try{
                    for (int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        tipoUsuarioList.add(new TipoUsuario(jsonObject.getInt("idTipoUsuario"),
                                jsonObject.getString("nombreTipoUsuario"),jsonObject.getString("descripcionTipoUsuario")));
                    }
                    tipoUsuario_adapter = new TipoUsuario_Adapter(RegistrarUsuario.this,tipoUsuarioList);
                    sp_tipoUsuario.setAdapter(tipoUsuario_adapter);



                }catch (JSONException e){
                    Toast.makeText(RegistrarUsuario.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarUsuario.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
    private void listadoDniTrabajador(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                trabajadorList = new ArrayList<>();
                try{
                    for (int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        trabajadorList.add(new Trabajador(jsonObject.getInt("idTrabajador"),
                                jsonObject.getString("perDni")));
                    }
                    trabajador_adapter = new trabajador_Adapter(RegistrarUsuario.this,trabajadorList);
                    sp_dniTrabajador.setAdapter(trabajador_adapter);



                }catch (JSONException e){
                    Toast.makeText(RegistrarUsuario.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarUsuario.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }
}