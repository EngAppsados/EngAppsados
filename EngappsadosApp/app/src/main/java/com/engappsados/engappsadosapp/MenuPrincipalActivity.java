package com.engappsados.engappsadosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipalActivity extends AppCompatActivity {

    public Button buttonReward;
    public Button buttonNoticias;
    public Button buttonTareas;
    public void init(){
        buttonReward = (Button)findViewById(R.id.buttonReward);
        buttonNoticias=(Button)findViewById(R.id.buttonNoticias);
        buttonTareas=(Button)findViewById(R.id.buttonTareas);

        buttonReward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent recompensa = new Intent(MenuPrincipalActivity.this, Rewards.class);
                startActivity(recompensa);
            }
        });
        buttonNoticias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent noticias = new Intent(MenuPrincipalActivity.this, Noticias.class);
                Intent nuevoUIn = new Intent(MenuPrincipalActivity.this, TareasActivity.class);
                startActivity(nuevoUIn);
            }
        });

        buttonTareas.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent tareas = new Intent(MenuPrincipalActivity.this, TareasActivity.class);
                startActivity(tareas);
            }
        });
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("holalala olalala");
        setContentView(R.layout.activity_menu_principal);
        init();

    }
}
