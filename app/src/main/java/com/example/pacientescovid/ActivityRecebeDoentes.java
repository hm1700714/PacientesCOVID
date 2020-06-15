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
        TextView textViewNomeUtente = (TextView) findViewById(R.id.textViewSintomas);
        textViewNomeUtente.setText(nome);

        String morada = intent.getStringExtra("morada");
        TextView textViewMoradaUtente = (TextView) findViewById(R.id.textViewDescricaoSintomas);
        textViewMoradaUtente.setText(morada);

        String contacto = intent.getStringExtra("contacto");
        TextView textViewContactoUtente = (TextView) findViewById(R.id.textViewContactoUtente);
        textViewContactoUtente.setText(contacto);

        //String datanascimento = intent.getStringExtra("datanascimento");
        //TextView textViewDataNascimento = (TextView) findViewById(R.id.textViewContactoUtente);
        //textViewDataNascimento.setText(datanascimento);

    }
}