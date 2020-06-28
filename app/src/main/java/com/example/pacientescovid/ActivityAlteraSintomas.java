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
import android.widget.Toast;

public class ActivityAlteraSintomas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSOR_LOADER_SINTOMAS = 0;

    private EditText editTextAlteraSintoma;
    private EditText editTextAlteradSintoma;

    private Sintomas sintomas;
    private Uri enderecoEditarSintomas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altera_sintomas);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        editTextAlteraSintoma = (EditText) findViewById(R.id.TextInputEditTextAlteraSintoma);
        editTextAlteradSintoma = (EditText) findViewById(R.id.TextInputEditTextAlteraDSintomas);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_SINTOMAS, null, this);

        Intent intent = getIntent();

        long idSintoma = intent.getLongExtra(ActivityMostraSintomas.ID_SINTOMAS,-1);

        if(idSintoma == -1){
            Toast.makeText(this, R.string.erro_ler_sintoma, Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        enderecoEditarSintomas = Uri.withAppendedPath(PacientesContentProvider.ENDERECO_SINTOMAS, String.valueOf(idSintoma));

        Cursor cursor = getContentResolver().query(enderecoEditarSintomas, BdTableSintomas.TODOS_CAMPOS,
                null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this, R.string.erro_ler_sintoma, Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        sintomas = Sintomas.fromCursor(cursor);

        editTextAlteraSintoma.setText(sintomas.getSintoma());
        editTextAlteradSintoma.setText(sintomas.getDescricaoSintoma());
    }

    public void AlterarSintomas(View view){

        String ConteudoSintoma = editTextAlteraSintoma.getText().toString();
        String ConteudoDescricaoSintoma = editTextAlteradSintoma.getText().toString();


        if (ConteudoSintoma.trim().isEmpty()){

            editTextAlteraSintoma.setError(getString(R.string.preencherNome));
            editTextAlteraSintoma.requestFocus();
            return;
        }

        if (ConteudoDescricaoSintoma.trim().isEmpty()) {

            editTextAlteradSintoma.setError(getString(R.string.preencherMorada));
            editTextAlteradSintoma.requestFocus();
            return;
        }

        String sintoma = editTextAlteraSintoma.getText().toString();
        String descricao = editTextAlteradSintoma.getText().toString();


        // Grava os dados do sintoma

        Sintomas sintomas = new Sintomas();

        sintomas.setSintoma(sintoma);
        sintomas.setDescricaoSintoma(descricao);


        try {
            getContentResolver().update( enderecoEditarSintomas, sintomas.getContentValues(), null, null);

            Toast.makeText(this, (getString(R.string.sintoma_alterado)), Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(this,(R.string.erro_alterar_sintoma), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
        finish();
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, PacientesContentProvider.ENDERECO_SINTOMAS, BdTableSintomas.TODOS_CAMPOS,
                null, null, BdTableSintomas.CAMPO_SINTOMAS);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}