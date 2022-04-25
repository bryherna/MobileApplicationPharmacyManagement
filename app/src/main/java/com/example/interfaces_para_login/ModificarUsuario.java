package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
import com.example.interfaces_para_login.Adaptadores.TipoUsuario_Adapter;
import com.example.interfaces_para_login.Adaptadores.trabajador_Adapter;
import com.example.interfaces_para_login.Entidades.Conexion;
import com.example.interfaces_para_login.Entidades.TipoUsuario;
import com.example.interfaces_para_login.Entidades.Trabajador;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ModificarUsuario extends AppCompatActivity {
    private Spinner sp_estadoUsuM,sp_dniTrabajadorM,sp_tipoUsuarioM;
    private String urlTipoUsuario, urlDniTrabajador;
    private RequestQueue requestQueue;


    private List<TipoUsuario> tipoUsuarioList;
    private TipoUsuario_Adapter tipoUsuario_adapter;

    private List<Trabajador> trabajadorList;
    private trabajador_Adapter trabajador_adapter;


    private String idTipoUsuario;
    private String idTrabajador;
    private int idUsuario;

    private EditText edt_nombreUsuarioM,edt_pass_usuarioM;

    //botones
    private Button btnModificarUsuario,btnEliminarUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_usuario);
        setTitle("MODIFICAR USUARIO");

        sp_estadoUsuM = (Spinner) findViewById(R.id.sp_estadoUsuM);
        btnModificarUsuario = (Button)findViewById(R.id.btnModificarUsuario);
        btnEliminarUsuario = (Button)findViewById(R.id.btnEliminarUsuario);
        edt_nombreUsuarioM = (EditText)findViewById(R.id.edt_nombreUsuarioM);
        edt_pass_usuarioM = (EditText)findViewById(R.id.edt_pass_usuarioM);
        String []estados = {"ACTIVO","INACTIVO"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,estados);
        sp_estadoUsuM.setAdapter(adapter);

        sp_tipoUsuarioM = (Spinner) findViewById(R.id.sp_tipoUsuarioM);
        sp_dniTrabajadorM = (Spinner)findViewById(R.id.sp_dniTrabajadorM);

        idTipoUsuario ="";
        idTrabajador="";

        idUsuario = Integer.parseInt(getIntent().getStringExtra("idUsuario"));
        System.out.println(idUsuario);
        String url = Conexion.URL+"ws_usuarios.php?idUsu="+idUsuario+"&listarPorIdUsuario";

        listarDatosUsuario(url);


        urlTipoUsuario = Conexion.URL+"ws_tipoUsuarios.php?listar";
        listadoTipoUsuario(urlTipoUsuario);
        urlDniTrabajador = Conexion.URL+"ws_trabajadores.php?listar";
        listadoDniTrabajador(urlDniTrabajador);

        btnModificarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarUsuario();

            }
        });
        btnEliminarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarUsuario();

            }
        });


        sp_tipoUsuarioM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTipoUsuario = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        sp_dniTrabajadorM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idTrabajador = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

    private void eliminarUsuario(){
        String url = Conexion.URL+"ws_usuarios.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ModificarUsuario.this,"SE ELIMINO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                Intent principal = new Intent(ModificarUsuario.this,ReporteUsuarios_List.class);
                startActivity(principal);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarUsuario.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("id",String.valueOf(idUsuario));
                parametros.put("eliminar","");

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(ModificarUsuario.this);
        requestQueue.add(stringRequest);
    }

    private void listarDatosUsuario(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
                try{
                    for(int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        edt_nombreUsuarioM.setText(jsonObject.getString("nombreUsuario"));
                        edt_pass_usuarioM.setText(jsonObject.getString("password_usuario"));



                    }

                }catch(JSONException e){
                    Toast.makeText(ModificarUsuario.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarUsuario.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void modificarUsuario(){
        String url = Conexion.URL+"ws_usuarios.php";
        final ProgressDialog loading = ProgressDialog.show(this,"Actualizando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(ModificarUsuario.this,"SE ACTUALIZO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                        Intent principal = new Intent(ModificarUsuario.this,ReporteUsuarios_List.class);
                        startActivity(principal);
                        finish();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        //Descartar el diálogo de progreso
                        loading.dismiss();

                        //Showing toast
                        Toast.makeText(ModificarUsuario.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                //Creación de parámetros
                Map<String,String> parametros = new Hashtable<String, String>();
                parametros.put("idUsuario", String.valueOf(idUsuario));
                parametros.put("nombreUsuario", edt_nombreUsuarioM.getText().toString() );
                parametros.put("idTipoUsuario", idTipoUsuario );
                parametros.put("idTrabajador",  idTrabajador );
                parametros.put("password_usuario",edt_pass_usuarioM.getText().toString());
                if(sp_estadoUsuM.getSelectedItem().toString().equalsIgnoreCase("ACTIVO")){
                    parametros.put("estadoUsuario",String.valueOf(1));
                }else{
                    parametros.put("estadoUsuario",String.valueOf(0));
                }

                parametros.put("actualizar","");

                return parametros;

            }
        };

        //Creación de una cola de solicitudes
        requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
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
                    tipoUsuario_adapter = new TipoUsuario_Adapter(ModificarUsuario.this,tipoUsuarioList);
                    sp_tipoUsuarioM.setAdapter(tipoUsuario_adapter);



                }catch (JSONException e){
                    Toast.makeText(ModificarUsuario.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarUsuario.this,error.toString(),Toast.LENGTH_SHORT).show();
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
                    trabajador_adapter = new trabajador_Adapter(ModificarUsuario.this,trabajadorList);
                    sp_dniTrabajadorM.setAdapter(trabajador_adapter);



                }catch (JSONException e){
                    Toast.makeText(ModificarUsuario.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarUsuario.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);


    }


}