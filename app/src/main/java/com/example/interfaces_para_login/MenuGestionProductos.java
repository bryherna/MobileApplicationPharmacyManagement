package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuGestionProductos extends AppCompatActivity {
    private Button btnAgregarProducto,btnListarProducto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gestion_productos);
        btnAgregarProducto = (Button) findViewById(R.id.btnAgregarProducto);
        btnListarProducto = (Button) findViewById(R.id.btnListarProducto);
        setTitle("MENU GESTION DE PRODUCTOS");

        btnListarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent listarPro = new Intent(MenuGestionProductos.this,ReporteProductos_List.class);
                startActivity(listarPro);
            }
        });

        btnAgregarProducto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registrarPro = new Intent(MenuGestionProductos.this,RegistrarProducto.class);
                startActivity(registrarPro);
                finish();
            }
        });
    }
}