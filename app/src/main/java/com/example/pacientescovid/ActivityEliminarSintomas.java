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

public class ActivityEliminarSintomas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    private static final int ID_CURSOR_LOADER_SINTOMAS = 0;

    private TextView textViewEliminarSintoma;
    private TextView textViewEliminardSintoma;

    private Sintomas sintomas;
    private Uri enderecoEliminarSintomas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_sintomas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        TextView textViewEliminarSintoma = (TextView) findViewById(R.id.textViewEliminarSintoma);
        TextView textViewEliminardSintoma = (TextView) findViewById(R.id.textViewEliminarDescricao);

        Intent intent = getIntent();

        long idSintoma = intent.getLongExtra(ActivityMostraSintomas.ID_SINTOMAS,-1);

        if(idSintoma == -1){
            Toast.makeText(this, "Erro: não foi possivel ler o Sintoma!", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        enderecoEliminarSintomas = Uri.withAppendedPath(PacientesContentProvider.ENDERECO_SINTOMAS, String.valueOf(idSintoma));

        Cursor cursor = getContentResolver().query(enderecoEliminarSintomas, BdTableSintomas.TODOS_CAMPOS,
                null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel ler o Pessoa!!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        sintomas = Sintomas.fromCursor(cursor);

        textViewEliminarSintoma.setText(sintomas.getSintoma());
        textViewEliminardSintoma.setText(sintomas.getDescricaoSintoma());
    }

    public void eliminarSintomas(View view) {
        int SintomasApagados = getContentResolver().delete(enderecoEliminarSintomas, null, null);

        if (SintomasApagados == 1) {
            Toast.makeText(this, "nao sei o que acontece", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "aconteceu", Toast.LENGTH_LONG).show();
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