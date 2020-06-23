package com.example.pacientescovid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class ActivityAlteraDoentes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSOR_LOADER_DOENTES = 0;

    private EditText editTextAlteraNome;
    private EditText editTextAlteraMorada;
    private EditText editTextAlteraContacto;
    private EditText editTextAlteradNascimento;

    private Doentes doentes;
    private Uri enderecoEditarDoentes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_doentes);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextAlteraNome = (EditText) findViewById(R.id.textInputEditTextAlteraNome);
        editTextAlteraMorada = (EditText) findViewById(R.id.textInputEditTextAlteraMorada);
        editTextAlteraContacto = (EditText) findViewById(R.id.textInputEditTextAlteraContacto);
        editTextAlteradNascimento = (EditText) findViewById(R.id.textInputEditTextAlteraNascimento);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_DOENTES, null, this);

        Intent intent = getIntent();

        long idDoente = intent.getLongExtra(ActivityMostraDoentes.ID_DOENTES,-1);

        if(idDoente == -1){
            Toast.makeText(this, "Erro: não foi possivel ler o carro!", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }



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