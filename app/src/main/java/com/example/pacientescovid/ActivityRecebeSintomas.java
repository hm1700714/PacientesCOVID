package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityRecebeSintomas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_sintomas);

        Intent intent = getIntent();

        String sintoma = intent.getStringExtra("sintoma");
        TextView textViewSintoma = (TextView) findViewById(R.id.textViewTemperatura);
        textViewSintoma.setText(sintoma);

        String descSintoma = intent.getStringExtra("descricaosintoma");
        TextView textViewDescricao = (TextView) findViewById(R.id.textViewMedicamentos);
        textViewDescricao.setText(descSintoma);
    }



}