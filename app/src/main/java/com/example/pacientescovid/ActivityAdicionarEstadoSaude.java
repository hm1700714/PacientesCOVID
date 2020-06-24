package com.example.pacientescovid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAdicionarEstadoSaude extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final int ID_CURSOR_LOADER_DOENTES = 0;

    private Spinner spinnerDoentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_estado_saude);

        spinnerDoentes = (Spinner) findViewById(R.id.spinnerRecebeDoentes);
        mostraDadosSpinnerDoentes(null);
        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_DOENTES, null, this);

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

        long idDoente = spinnerDoentes.getSelectedItemId();

        EstadoSaude eSaude = new EstadoSaude();

        eSaude.setIdDoente(idDoente);
        eSaude.setHoraVisita(horaVisita);
        eSaude.setDiaVisita(diaVisita);
        eSaude.setTemperatura(temperatura);
        eSaude.setMedicamentos(medicamentos);

        try {
            this.getContentResolver().insert(PacientesContentProvider.ENDERECO_ESTADOSAUDE, Converte.estadosaudeToContentValues(eSaude));
            Toast.makeText(this,"Estado adicionado com sucesso", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            Toast.makeText(this, "Falha ao adicionar estado", Toast.LENGTH_SHORT).show();
        }
        finish();

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, PacientesContentProvider.ENDERECO_DOENTES, BdTableDoentes.TODOS_CAMPOS,
                null, null, BdTableDoentes.CAMPO_NOME);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mostraDadosSpinnerDoentes(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mostraDadosSpinnerDoentes(null);
    }

    private void mostraDadosSpinnerDoentes(Cursor data) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data,
                new String[]{BdTableDoentes.CAMPO_NOME},
                new int[]{android.R.id.text1}
        );
        spinnerDoentes.setAdapter(adapter);
    }

}