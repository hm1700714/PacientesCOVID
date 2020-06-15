package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityRecebeDoentes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_doentes);

        Intent intent = getIntent();

        String nome = intent.getStringExtra("nome");
        TextView textViewNomeUtente = (TextView) findViewById(R.id.textViewNomeUtente);
        textViewNomeUtente.setText(nome);

        String morada = intent.getStringExtra("morada");
        TextView textViewMoradaUtente = (TextView) findViewById(R.id.textViewMoradaUtente);
        textViewMoradaUtente.setText(morada);

        String contacto = intent.getStringExtra("contacto");
        TextView textViewContactoUtente = (TextView) findViewById(R.id.textViewContactoUtente);
        textViewContactoUtente.setText(contacto);


        //int idade = intent.getIntExtra(App.IDADE, 18);
        //TextView textViewIdade = (TextView) findViewById(R.id.textViewIdade);
        //textViewIdade.setText(idade);
    }
}