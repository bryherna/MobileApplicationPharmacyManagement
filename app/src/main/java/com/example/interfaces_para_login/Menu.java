package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class Menu extends AppCompatActivity {
    ImageView btn_usuarios, btn_clientes,btn_crudProductos,btn_ventas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        btn_usuarios = (ImageView)findViewById(R.id.btn_usuarios);
        btn_clientes = (ImageView)findViewById(R.id.btn_clientes);
        btn_crudProductos = (ImageView) findViewById(R.id.btn_crudProductos);

        setTitle("MENU");


        btn_usuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GU = new Intent(Menu.this,MenuGestionUsuarios.class);
                startActivity(GU);

            }
        });
        btn_crudProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent GP = new Intent(Menu.this,MenuGestionProductos.class);
                startActivity(GP);

            }
        });

        btn_clientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RC = new Intent(Menu.this,ReporteClientes_List.class);
                startActivity(RC);
            }
        });


    }
}