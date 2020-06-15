package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class ActivityAdicionarDoente extends AppCompatActivity {

    Button dataButton2;
    TextView dataTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_doente);

        dataButton2 = findViewById(R.id.buttonDataNascimento);
        dataTextView = findViewById(R.id.textViewDataNascimento);

        dataButton2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DataNascimentoButton();
            }
        });
    }

    private void DataNascimentoButton(){

        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                String dataString = date + " " + month + " " + year;
                dataTextView.setText(dataString);
            }
        }, ano, mes, dia);

        datePickerDialog.show();
    }

    public void enviaMensagem(View view) {

        Intent intent = new Intent(this, ActivityRecebeDoentes.class);

        //Permite ir buscar a caixa de edição de texto

        EditText editTextNome = (EditText) findViewById(R.id.TestInputEditTextInserirNome);
        String nome = editTextNome.getText().toString();

        //verificação de dados para o nome

        if(nome.length() == 0){
            editTextNome.setError(getString(R.string.preencherNome));
            editTextNome.requestFocus();
            return;
        }

        EditText editTextMorada = (EditText) findViewById(R.id.TestInputEditTextInserirMorada);
        String morada = editTextMorada.getText().toString();

        if(morada.length() < 9){
            editTextMorada.setError(getString(R.string.preencherMorada));
            editTextMorada.requestFocus();
            return;
        }

        EditText editTextContacto = (EditText) findViewById(R.id.TestInputEditTextInserirContacto);
        String contacto = editTextContacto.getText().toString();

        if(contacto.length() < 9){
            editTextContacto.setError(getString(R.string.preencherContacto));
            editTextContacto.requestFocus();
            return;
        }


        intent.putExtra("nome", nome);
        intent.putExtra("morada", morada);
        intent.putExtra("contacto", contacto);
        //intent.putExtra(App.IDADE, idade);

        startActivity(intent);

        //todo: enviar mensagem
    }


}
