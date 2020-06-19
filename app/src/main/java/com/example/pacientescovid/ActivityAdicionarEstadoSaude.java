package com.example.pacientescovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ActivityAdicionarEstadoSaude extends AppCompatActivity {

    Button dataButton, horasButton;
    TextView dataTextView, horasTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_estado_saude);
/*
        dataButton = findViewById(R.id.buttonDataSaude);
        horasButton = findViewById(R.id.buttonHoraSaude);
        dataTextView = findViewById(R.id.textViewDiaVisita);
        horasTextView = findViewById(R.id.textViewHoraVisita);

        dataButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                testeDataButton();
            }
        });

        horasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testeHorasButton();
            }
        });
 */
    }
/*
    private void testeDataButton(){

        Calendar calendario = Calendar.getInstance();

        int ano = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DATE);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int date) {
                //String dataString = date + " " + month + " " + year;
                //dataTextView.setText(dataString);

                Calendar calendario1 = Calendar.getInstance();
                calendario1.set(Calendar.YEAR, year);
                calendario1.set(Calendar.MONTH, month);
                calendario1.set(Calendar.DATE, date);
                String dateText = DateFormat.format("EEEE, MMMM d, yyyy", calendario1).toString();

                dataTextView.setText(dateText);
            }
        }, ano, mes, dia);

        datePickerDialog.show();
    }

    private void testeHorasButton(){

        Calendar calendario = Calendar.getInstance();

        int hora = calendario.get(Calendar.HOUR);
        int minutos = calendario.get(Calendar.MINUTE);

        boolean formato24Horas = DateFormat.is24HourFormat(this); //Vê o formato de horas que o utilizador tem no seu dispositivo

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hour, int minute) {
                //String horasString =  hour + " horas e " + minute + " minutos " ;
                String horasString =  hour + " : " + minute;
                horasTextView.setText(horasString);
            }
        },hora, minutos, formato24Horas );// true para mostrar as horas no formato de 24h e false para formato de 12h

        timePickerDialog.show();
    }

*/
    public void enviaEstadoSaude(View view) {

        Intent intent = new Intent(this, ActivityRecebeEstadoSaude.class);

        //Permite ir buscar a caixa de edição de texto

        EditText editTextHoraVisita = (EditText) findViewById(R.id.TextInputEditTextInserirHoraVisita);
        String horaVisita = editTextHoraVisita.getText().toString();

        //verificação de dados para o nome

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

        if(medicamentos.length() == 0){
            editTextMedicamentos.setError(getString(R.string.medicamentos_obrigatorios));
            editTextMedicamentos.requestFocus();
            return;
        }

        intent.putExtra("horaVisita", horaVisita);
        intent.putExtra("diaVisita", diaVisita);
        intent.putExtra("febre", temperatura);
        intent.putExtra("medicamentos", medicamentos);


        startActivity(intent);

        //todo: enviar mensagem
    }


}