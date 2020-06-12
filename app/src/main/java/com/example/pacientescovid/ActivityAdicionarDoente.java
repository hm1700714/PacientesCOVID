package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
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

}
