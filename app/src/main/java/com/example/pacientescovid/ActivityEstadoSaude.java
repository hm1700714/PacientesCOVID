package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityEstadoSaude extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_saude);
    }

    public void passaAdicionarEstadoSaude (View view){

        Intent intent = new Intent(this, ActivityAdicionarEstadoSaude.class);
        startActivity(intent);
    }

    public void passaVerEstadoSaude (View view){
        Intent intent = new Intent(this, ActivityMostraEstado.class);
        startActivity(intent);
    }
}