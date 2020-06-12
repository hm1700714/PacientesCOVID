package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActivityDoentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doentes);
    }

    public void passaAdicionarDoente (View view){

        Intent intent = new Intent(this, ActivityAdicionarDoente.class);
        startActivity(intent);
    }
}