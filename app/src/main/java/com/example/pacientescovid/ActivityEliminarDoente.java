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
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityEliminarDoente extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSOR_LOADER_DOENTES = 0;

    private TextView textViewNome;
    private TextView textViewMorada;
    private TextView textViewContacto;
    private TextView textViewDataNascimento;
    private Doentes doentes;
    private Uri enderecoEliminarDoente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_doente);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewNome = (TextView) findViewById(R.id.textViewApagarNome);
        TextView textViewMorada = (TextView) findViewById(R.id.textViewApagarMorada);
        TextView textViewContacto = (TextView) findViewById(R.id.textViewApagarContacto);
        TextView textViewDataNascimento = (TextView) findViewById(R.id.textViewApagarDNascimento);

        Intent intent = getIntent();

        long idDoente = intent.getLongExtra(ActivityMostraDoentes.ID_DOENTES,-1);

        if(idDoente == -1){
            Toast.makeText(this, R.string.erro_ler_doente, Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        enderecoEliminarDoente = Uri.withAppendedPath(PacientesContentProvider.ENDERECO_DOENTES, String.valueOf(idDoente));

        Cursor cursor = getContentResolver().query(enderecoEliminarDoente, BdTableDoentes.TODOS_CAMPOS,
                null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,R.string.erro_ler_doente, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        doentes = Doentes.fromCursor(cursor);

        textViewNome.setText(doentes.getNomeUtente());
        textViewMorada.setText(doentes.getMoradaUtente());
        textViewContacto.setText(doentes.getContactoUtente());
        textViewDataNascimento.setText(doentes.getDataNascimentoUtente());
    }

    public void eliminarDoentes (View view) {
        int DoentesApagados = getContentResolver().delete(enderecoEliminarDoente, null, null);

        if (DoentesApagados == 1) {
            Toast.makeText(this, R.string.doente_apagado, Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
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