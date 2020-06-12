package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivitySintomas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sintomas);
    }

    public void passaAdicionarSintomas (View view){

        Intent intent = new Intent(this, ActivityAdicionarSintomas.class);
        startActivity(intent);
    }

}