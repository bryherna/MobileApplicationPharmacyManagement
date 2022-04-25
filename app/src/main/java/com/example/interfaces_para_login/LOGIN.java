package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.interfaces_para_login.Entidades.Conexion;

import java.util.HashMap;
import java.util.Map;

public class LOGIN extends AppCompatActivity {
    private Button btn_login, btn_registrarse;
    private EditText edt_ingresar_usuario,edt_ingresar_contra;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("LOGIN");


        btn_login = findViewById(R.id.btn_login);
        btn_registrarse = findViewById(R.id.btn_registrarse);
        edt_ingresar_usuario =(EditText)findViewById(R.id.edt_ingresar_usuario);
        edt_ingresar_contra = (EditText) findViewById(R.id.edt_ingresar_contra);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logearse();
            }
        });

    }

    private void logearse(){
        String url = Conexion.URL+"ws_usuarios.php";
        final ProgressDialog progressDialog = ProgressDialog.show(LOGIN.this,"Validando Usuario","Espere un momento");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(response.isEmpty()){
                    progressDialog.cancel();
                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(LOGIN.this);
                    alertdialog.setTitle("ERROR DE LOGIN");
                    alertdialog.setMessage("USUARIO O CLAVE INCORRECTO".toLowerCase());
                    AlertDialog dialog= alertdialog.create();
                    dialog.show();

                }else{
                    progressDialog.cancel();
                    Intent login = new Intent(LOGIN.this,Menu.class);
                    startActivity(login);
                    finish();

                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LOGIN.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> parametros = new HashMap<String,String>();
                parametros.put("nomUsuario",edt_ingresar_usuario.getText().toString());
                parametros.put("passwordUsuario",edt_ingresar_contra.getText().toString());
                parametros.put("valida_login","");

                return parametros;
            }
        };
        requestQueue= Volley.newRequestQueue(LOGIN.this);
        requestQueue.add(stringRequest);
    }
}