package com.example.interfaces_para_login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuGestionUsuarios extends AppCompatActivity {
    Button btnAgregarUsuario, btnListarUsuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_gestion_usuarios);
        btnAgregarUsuario = (Button) findViewById(R.id.btnAgregarUsuario);
        btnListarUsuarios = (Button) findViewById(R.id.btnListarUsuarios);

        setTitle("MENU GESTION DE USUARIOS");

        btnAgregarUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent RU = new Intent(MenuGestionUsuarios.this,RegistrarUsuario.class);
                startActivity(RU);
                finish();
            }
        });
        btnListarUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LU = new Intent(MenuGestionUsuarios.this,ReporteUsuarios_List.class);
                startActivity(LU);
            }
        });

    }
}