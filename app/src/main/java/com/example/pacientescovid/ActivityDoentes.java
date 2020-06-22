package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

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

    public void passaVerDoente (View view){
        Intent intent = new Intent(this, ActivityMostraDoentes.class);
        startActivity(intent);
    }
}