package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
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
import com.example.interfaces_para_login.Adaptadores.Presentacion_Adapter;
import com.example.interfaces_para_login.Entidades.Conexion;
import com.example.interfaces_para_login.Entidades.Laboratorio;
import com.example.interfaces_para_login.Entidades.Presentacion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class ModificarProducto extends AppCompatActivity {
    private Spinner spPresentacionM, spLaboratorioM;
    private String urlPresencion,urlLaboratorio;
    private RequestQueue requestQueue;

    private List<Presentacion> presentacionList;
    private Presentacion_Adapter presentacion_adapter;

    private List<Laboratorio> laboratorioList;
    private Laboratorio_Adapter laboratorio_adapter;


    private Button btnModificarPro,btnEliminarPro;
    private EditText edt_nombreProductoM,edt_precioVentaM,edt_descripcionM,edt_stockM,edt_fechaM,edt_loteM;
    private String idlaboratorio;
    private String idpresentacion;

    private int id_producto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar_producto);
        spPresentacionM = (Spinner) findViewById(R.id.spPresentacionM);
        spLaboratorioM = (Spinner) findViewById(R.id.spLaboratorioM);
        btnModificarPro = (Button) findViewById(R.id.btnModificarPro);
        btnEliminarPro = (Button) findViewById(R.id.btnEliminarPro);

        //INICIALIZAR EDIT TEXT

        edt_nombreProductoM = (EditText)findViewById(R.id.edt_nombreProductoM);
        edt_precioVentaM = (EditText)findViewById(R.id.edt_precioVentaM);
        edt_descripcionM = (EditText)findViewById(R.id.edt_descripcionM);
        edt_stockM = (EditText)findViewById(R.id.edt_stockM);
        edt_fechaM = (EditText)findViewById(R.id.edt_fechaM);
        edt_loteM = (EditText)findViewById(R.id.edt_loteM);

        setTitle("MODIFICAR PRODUCTO");
        urlPresencion = Conexion.URL +"ws_presentacion.php?listar";
        listadoPresentacion(urlPresencion);
        urlLaboratorio = Conexion.URL + "ws_laboratorio.php?listar";
        listadoLaboratorios(urlLaboratorio);

        idlaboratorio = "";
        idpresentacion  = "";

        id_producto = Integer.parseInt(getIntent().getStringExtra("producto_id"));
        String url = Conexion.URL+"ws_producto.php?idPro="+id_producto+"&producto_por_id";

        listarDatosProducto(url);

        btnModificarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modificarProducto();
            }
        });
        btnEliminarPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eliminarProducto();
            }
        });
        edt_fechaM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendario();
            }
        });
        spPresentacionM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idpresentacion = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spLaboratorioM.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idlaboratorio = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }
    private void eliminarProducto(){

        String url = Conexion.URL+"ws_producto.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(ModificarProducto.this,"SE ELIMINO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                Intent principal = new Intent(ModificarProducto.this,ReporteProductos_List.class);
                startActivity(principal);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarProducto.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("idPro",String.valueOf(id_producto));
                parametros.put("eliminar","");

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(ModificarProducto.this);
        requestQueue.add(stringRequest);
    }

    private void modificarProducto(){
        String url = Conexion.URL+"ws_producto.php";

        final ProgressDialog loading = ProgressDialog.show(this,"Actualizando...","Espere por favor...",false,false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        loading.dismiss();
                        Toast.makeText(ModificarProducto.this,"SE ACTUALIZO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                        Intent principal = new Intent(ModificarProducto.this,ReporteProductos_List.class);
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
                        Toast.makeText(ModificarProducto.this, volleyError.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                //Creación de parámetros
                Map<String,String> parametros = new Hashtable<String, String>();
                parametros.put("idProducto", String.valueOf(id_producto));
                parametros.put("idPresentacion", idpresentacion );
                parametros.put("idLaboratorio", idlaboratorio );
                parametros.put("nomProducto",  edt_nombreProductoM.getText().toString() );
                parametros.put("precioVenta", edt_precioVentaM.getText().toString() );
                parametros.put("descripcion", edt_descripcionM.getText().toString() );
                parametros.put("stock",  edt_stockM.getText().toString());
                parametros.put("caducidad", edt_fechaM.getText().toString() );
                parametros.put("lote", edt_loteM.getText().toString() );
                parametros.put("actualizar","");

                return parametros;

            }
        };

        //Creación de una cola de solicitudes
        requestQueue = Volley.newRequestQueue(this);

        //Agregar solicitud a la cola
        requestQueue.add(stringRequest);

    }
    private void calendario(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_TRADITIONAL, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                edt_fechaM.setText(i+"-"+i1+"-"+i2);

            }
        }, 1998,01,01);
        datePickerDialog.show();
    }

    private void listarDatosProducto(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject =null;
                try{
                    for(int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        edt_nombreProductoM.setText(jsonObject.getString("nomProducto"));
                        edt_precioVentaM.setText(String.valueOf(jsonObject.getDouble("precioVenta")));
                        edt_descripcionM.setText(jsonObject.getString("descripcion"));
                        edt_stockM.setText(String.valueOf(jsonObject.getInt("stock")));
                        edt_fechaM.setText(jsonObject.getString("caducidad"));
                        edt_loteM.setText(jsonObject.getString("lote"));



                    }

                }catch(JSONException e){
                    Toast.makeText(ModificarProducto.this,e.toString(),Toast.LENGTH_SHORT).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarProducto.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }

    private void listadoLaboratorios(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                laboratorioList = new ArrayList<>();
                try{
                    for (int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        laboratorioList.add(new Laboratorio(jsonObject.getInt("idLaboratorio"),
                                jsonObject.getString("nomLaboratorio")));
                    }
                    laboratorio_adapter = new Laboratorio_Adapter(ModificarProducto.this,laboratorioList);
                    spLaboratorioM.setAdapter(laboratorio_adapter);



                }catch (JSONException e){
                    Toast.makeText(ModificarProducto.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarProducto.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

    private void listadoPresentacion(String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                presentacionList = new ArrayList<>();
                try{
                    for (int i=0; i<response.length();i++){
                        jsonObject = response.getJSONObject(i);
                        presentacionList.add(new Presentacion(jsonObject.getInt("idPresentacion"),
                                jsonObject.getString("nomPresentacion")));
                    }
                    presentacion_adapter = new Presentacion_Adapter(ModificarProducto.this,presentacionList);
                    spPresentacionM.setAdapter(presentacion_adapter);



                }catch (JSONException e){
                    Toast.makeText(ModificarProducto.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ModificarProducto.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }

}