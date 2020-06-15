package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ActivityRecebeEstadoSaude extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recebe_estado_saude);

        Intent intent = getIntent();

        String temperatura = intent.getStringExtra("febre");
        TextView textViewTemperatura = (TextView) findViewById(R.id.textViewTemperatura);
        textViewTemperatura.setText(temperatura);

        String medicamentos = intent.getStringExtra("medicamentos");
        TextView textViewMedicamentos = (TextView) findViewById(R.id.textViewMedicamentos);
        textViewMedicamentos.setText(medicamentos);
    }
}