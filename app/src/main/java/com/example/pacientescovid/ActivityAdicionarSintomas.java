package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityAdicionarSintomas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_sintomas);
    }

    public void enviaSintomas(View view) {

        //Intent intent = new Intent(this, ActivityRecebeSintomas.class);

        //Permite ir buscar a caixa de edição de texto

        EditText editTextSintomas = (EditText) findViewById(R.id.TestInputEditTextInserirSintoma);
        String sintomas = editTextSintomas.getText().toString();

        //verificação de dados para o nome

        if(sintomas.length() == 0){
            editTextSintomas.setError(getString(R.string.sintomas_obrigatorios));
            editTextSintomas.requestFocus();
            return;
        }

        EditText editTextDescricaoSintomas = (EditText) findViewById(R.id.TestInputEditTextInserirDescricao);
        String descSintomas = editTextDescricaoSintomas.getText().toString();

        if(descSintomas.length() == 0){
            editTextDescricaoSintomas.setError(getString(R.string.descricao_sintomas_obrigatoria));
            editTextDescricaoSintomas.requestFocus();
            return;
        }

        Sintomas sintoma = new Sintomas();
        sintoma.setSintoma(sintomas);
        sintoma.setDescricaoSintoma(descSintomas);

        try {
            this.getContentResolver().insert(PacientesContentProvider.ENDERECO_SINTOMAS, Converte.sintomasToContentValues(sintoma));
            Toast.makeText(this, R.string.sintoma_adicionado, Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, R.string.falha_sintoma, Toast.LENGTH_SHORT).show();
        }
        finish();

/*
        intent.putExtra("sintoma", sintomas);
        intent.putExtra("descricaosintoma", descSintomas);
        startActivity(intent);
*/
    }
}