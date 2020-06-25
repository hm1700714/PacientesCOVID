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

    private TextView editTextNome;
    private EditText editTextAlteraHoras;
    private EditText editTextAlteraDias;
    private EditText editTextAlteraTemperaturas;
    private EditText editTextAlteraMedicamentos;

    //private Spinner MostraDoentesSpinner;

    private boolean carregaDoentes = false;
    private boolean atualizaDoentes = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_estado);

        //MostraDoentesSpinner = (Spinner) findViewById(R.id.spinnerAlteraEstado);

        editTextAlteraHoras = (EditText) findViewById(R.id.TextInputEditTextHoras);
        editTextAlteraDias = (EditText) findViewById(R.id.TextInputEditTextDias);
        editTextAlteraTemperaturas = (EditText) findViewById(R.id.TextInputEditTextTemperatura);
        editTextAlteraMedicamentos = (EditText) findViewById(R.id.TextInputEditTextMedicamentos);

    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}