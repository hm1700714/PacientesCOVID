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


        mostraDadosSpinnerPessoas(null);

        Intent intent = getIntent();

        //se colocar ID_DOENTE ele nao passa daqui
/*
        estadoSaude = (EstadoSaude) intent.getSerializableExtra("Estado");

        editTextAlteraHoras.setText(estadoSaude.getHoraVisita());
        editTextAlteraDias.setText(estadoSaude.getDiaVisita());
        editTextAlteraTemperaturas.setText("" + estadoSaude.getTemperatura());
        editTextAlteraMedicamentos.setText(estadoSaude.getMedicamentos());

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_DOENTES, null, this);
        actualizaPessoasSelecionada();
*/
    }

    private void actualizaPessoasSelecionada() {
        if (!carregaDoentes) return;
        if (atualizaDoentes) return;

        long idDoente = estadoSaude.getIdDoente();

        for (int i= 0; i < MostraDoentesSpinner.getCount(); i++) {
            if (MostraDoentesSpinner.getItemIdAtPosition(i) == idDoente) {
                MostraDoentesSpinner.setSelection(i, true);
                break;
            }
        }
        atualizaDoentes = true;
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