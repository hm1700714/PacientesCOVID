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

        String horaVisita = intent.getStringExtra("horaVisita");
        TextView textViewHoraVisita = (TextView) findViewById(R.id.textViewHoraVisita);
        textViewHoraVisita.setText(horaVisita);

        String diaVisita = intent.getStringExtra("diaVisita");
        TextView textViewDiaVisita = (TextView) findViewById(R.id.textViewDiaVisita);
        textViewDiaVisita.setText(diaVisita);

        String temperatura = intent.getStringExtra("febre");
        TextView textViewTemperatura = (TextView) findViewById(R.id.textViewTemperatura);
        textViewTemperatura.setText(temperatura);

        String medicamentos = intent.getStringExtra("medicamentos");
        TextView textViewMedicamentos = (TextView) findViewById(R.id.textViewMedicamentos);
        textViewMedicamentos.setText(medicamentos);
    }
}