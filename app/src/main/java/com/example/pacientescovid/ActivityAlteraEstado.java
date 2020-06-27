package com.example.pacientescovid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;


import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityAlteraEstado extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSOR_LOADER_DOENTES = 0;

    private EstadoSaude estadoSaude;
    private Spinner MostraDoentesSpinner;


    private EditText editTextAlteraHoras;
    private EditText editTextAlteraDias;
    private EditText editTextAlteraTemperaturas;
    private EditText editTextAlteraMedicamentos;

    private boolean carregaDoentes = false;
    private boolean atualizaDoentes = false;

    private Uri enderecoEditarEstado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_estado);

        MostraDoentesSpinner = (Spinner) findViewById(R.id.spinnerAlteraDoentes);
        editTextAlteraHoras = (EditText) findViewById(R.id.TextInputEditTextHoras);
        editTextAlteraDias = (EditText) findViewById(R.id.TextInputEditTextDias);
        editTextAlteraTemperaturas = (EditText) findViewById(R.id.TextInputEditTextTemperatura);
        editTextAlteraMedicamentos = (EditText) findViewById(R.id.TextInputEditTextMedicamentos);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_DOENTES, null, this);

        mostraDadosSpinnerPessoas(null);

        Intent intent = getIntent();

        //se colocar ID_DOENTE ele nao passa daqui

        long idDoente = intent.getLongExtra(ActivityMostraEstado.ID_DOENTE,-1);

        if(idDoente == -1){
            Toast.makeText(this, "Erro: não passa daqui!", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        enderecoEditarEstado = Uri.withAppendedPath(PacientesContentProvider.ENDERECO_ESTADOSAUDE, String.valueOf(idDoente));

        Cursor cursor = getContentResolver().query(enderecoEditarEstado, BdTableEstadoSaude.TODOS_CAMPOS,
                null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel ler o Doente!!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        estadoSaude = EstadoSaude.fromCursor(cursor);

        editTextAlteraHoras.setText(estadoSaude.getHoraVisita());
        editTextAlteraDias.setText(estadoSaude.getDiaVisita());
        editTextAlteraTemperaturas.setText(estadoSaude.getTemperatura());
        editTextAlteraMedicamentos.setText(estadoSaude.getMedicamentos());

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_DOENTES, null, this);

    }

    private void mostraDadosSpinnerPessoas(Cursor data) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data,
                new String[]{BdTableDoentes.CAMPO_NOME},
                new int[]{android.R.id.text1}
        );
        MostraDoentesSpinner.setAdapter(adapter);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, PacientesContentProvider.ENDERECO_DOENTES, BdTableDoentes.TODOS_CAMPOS,
                null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}