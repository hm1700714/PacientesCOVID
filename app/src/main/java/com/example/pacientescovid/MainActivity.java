package com.example.pacientescovid;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void passaDoente (View view){

        Intent intent = new Intent(this, ActivityDoentes.class);
        startActivity(intent);
    }

    public void passaEstadoSaude (View view){

        Intent intent = new Intent(this, ActivityEstadoSaude.class);
        startActivity(intent);
    }

    public void passaSintomas (View view){

        Intent intent = new Intent(this, ActivitySintomas.class);
        startActivity(intent);
    }
}