package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
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
import java.util.List;
import java.util.Map;

public class RegistrarProducto extends AppCompatActivity {
    private Spinner spPresentacion, spLaboratorio;
    private String urlPresencion,urlLaboratorio;
    private RequestQueue requestQueue;

    private List<Presentacion> presentacionList;
    private Presentacion_Adapter presentacion_adapter;

    private List<Laboratorio> laboratorioList;
    private Laboratorio_Adapter laboratorio_adapter;

    //botones
    private Button btnRegistrarProducto,btn_cancelar_producto;

    //text edit

    private EditText edt_nombreProducto,edt_precioVenta,edt_descripcion,edt_stock,edt_fecha,edt_lote;
    private String idlaboratorio;
    private String idpresentacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_producto);

        spPresentacion = (Spinner) findViewById(R.id.spPresentacion);
        spLaboratorio = (Spinner) findViewById(R.id.spLaboratorio);
        btnRegistrarProducto = (Button)findViewById(R.id.btnRegistrarProducto);
        btn_cancelar_producto = (Button)findViewById(R.id.btn_cancelar_pro);

        //inicializar edit text
        edt_nombreProducto = (EditText)findViewById(R.id.edt_nombreProducto);
        edt_precioVenta = (EditText)findViewById(R.id.edt_precioVenta);
        edt_descripcion = (EditText)findViewById(R.id.edt_descripcion);
        edt_stock = (EditText)findViewById(R.id.edt_stock);
        edt_fecha = (EditText)findViewById(R.id.edt_fecha);
        edt_lote = (EditText)findViewById(R.id.edt_lote);

        setTitle("REGISTRAR PRODUCTO");

        urlPresencion = Conexion.URL +"ws_presentacion.php?listar";
        listadoPresentacion(urlPresencion);
        urlLaboratorio = Conexion.URL + "ws_laboratorio.php?listar";
        listadoLaboratorios(urlLaboratorio);

        idlaboratorio = "";
        idpresentacion  = "";

        btnRegistrarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registrarProducto();
            }
        });
        btn_cancelar_producto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancelar = new Intent(RegistrarProducto.this,MenuGestionProductos.class);
                startActivity(cancelar);
                finish();
            }
        });

        edt_fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendario();
            }
        });
        spPresentacion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idpresentacion = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spLaboratorio.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                idlaboratorio = view.getTag().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




    }
    private void registrarProducto(){
        String url = Conexion.URL+"ws_producto.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(RegistrarProducto.this,"SE REGISTRO CORRECTAMENTE",Toast.LENGTH_SHORT).show();
                Intent principal = new Intent(RegistrarProducto.this,MenuGestionProductos.class);
                startActivity(principal);
                finish();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarProducto.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> parametros = new HashMap<String,String>();
                //parametros.put("idProducto", String.valueOf(0) );
                parametros.put("idPresentacion", idpresentacion );
                parametros.put("idLaboratorio", idlaboratorio );
                parametros.put("nomProducto",  edt_nombreProducto.getText().toString() );
                parametros.put("precioVenta", edt_precioVenta.getText().toString() );
                parametros.put("descripcion", edt_descripcion.getText().toString() );
                parametros.put("stock",  edt_stock.getText().toString());
                parametros.put("caducidad", edt_fecha.getText().toString() );
                parametros.put("lote", edt_lote.getText().toString() );
                parametros.put("insertar","");

                return parametros;
            }
        };
        requestQueue = Volley.newRequestQueue(RegistrarProducto.this);
        requestQueue.add(stringRequest);

    }
    private void calendario(){
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, AlertDialog.THEME_TRADITIONAL, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                i1=i1+1;
                edt_fecha.setText(i+"-"+i1+"-"+i2);

            }
        }, 1998,01,01);
        datePickerDialog.show();
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
                    laboratorio_adapter = new Laboratorio_Adapter(RegistrarProducto.this,laboratorioList);
                    spLaboratorio.setAdapter(laboratorio_adapter);



                }catch (JSONException e){
                    Toast.makeText(RegistrarProducto.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarProducto.this,error.toString(),Toast.LENGTH_SHORT).show();
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
                    presentacion_adapter = new Presentacion_Adapter(RegistrarProducto.this,presentacionList);
                    spPresentacion.setAdapter(presentacion_adapter);



                }catch (JSONException e){
                    Toast.makeText(RegistrarProducto.this,e.toString(),Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(RegistrarProducto.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);

    }
}