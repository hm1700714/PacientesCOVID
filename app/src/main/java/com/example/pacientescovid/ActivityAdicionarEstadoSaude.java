package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAdicionarEstadoSaude extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_estado_saude);

    }

    public void enviaEstadoSaude(View view) {

        //Intent intent = new Intent(this, ActivityRecebeEstadoSaude.class);

        //Permite ir buscar a caixa de edição de texto

        EditText editTextHoraVisita = (EditText) findViewById(R.id.TextInputEditTextInserirHoraVisita);
        String horaVisita = editTextHoraVisita.getText().toString();


        if(horaVisita.length() == 0){
            editTextHoraVisita.setError(getString(R.string.Hora_visita_obrigatoria));
            editTextHoraVisita.requestFocus();
            return;
        }

        EditText editTextDiaVisita = (EditText) findViewById(R.id.TextInputEditTextInserirDiaVisita);
        String diaVisita = editTextDiaVisita.getText().toString();

        if(diaVisita.length() == 0){
            editTextDiaVisita.setError(getString(R.string.Dia_visita_obrigatoria));
            editTextDiaVisita.requestFocus();
            return;
        }

        EditText editTextTemperatura = (EditText) findViewById(R.id.TestInputEditTextInserirTemperatura);
        String temperatura = editTextTemperatura.getText().toString();

        //verificação de dados para o nome

        if(temperatura.length() == 0){
            editTextTemperatura.setError(getString(R.string.temperatura_obrigatoria));
            editTextTemperatura.requestFocus();
            return;
        }

        EditText editTextMedicamentos = (EditText) findViewById(R.id.TestInputEditTextInserirMedicamentos);
        String medicamentos = editTextMedicamentos.getText().toString();

        if(medicamentos.length() == 0) {
            editTextMedicamentos.setError(getString(R.string.medicamentos_obrigatorios));
            editTextMedicamentos.requestFocus();
            return;
        }

        EstadoSaude eSaude = new EstadoSaude();
        eSaude.setSintoma(horaVisita);
        eSaude.setSintoma(diaVisita);
        eSaude.setSintoma(sintomas);
        eSaude.setDescricaoSintoma(descSintomas);

        try {
            this.getContentResolver().insert(PacientesContentProvider.ENDERECO_SINTOMAS, Converte.sintomasToContentValues(eSaude));
            Toast.makeText(this,"Doente adicionado com sucesso", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Falha ao adicionar doente", Toast.LENGTH_SHORT).show();
        }
        finish();



    }




}