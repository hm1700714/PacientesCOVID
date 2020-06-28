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

        enderecoEditarDoentes = Uri.withAppendedPath(PacientesContentProvider.ENDERECO_DOENTES, String.valueOf(idDoente));

        Cursor cursor = getContentResolver().query(enderecoEditarDoentes, BdTableDoentes.TODOS_CAMPOS,
                null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"Erro não foi possivel ler o Doente!!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        doentes = Doentes.fromCursor(cursor);

        editTextAlteraNome.setText(doentes.getNomeUtente());
        editTextAlteraMorada.setText(doentes.getMoradaUtente());
        editTextAlteraContacto.setText(doentes.getContactoUtente());
        editTextAlteradNascimento.setText(doentes.getDataNascimentoUtente());

    }

    public void AlterarDoentes(View view){

        String ConteudoNomeDoente = editTextAlteraNome.getText().toString();
        String ConteudoMoradaDoente = editTextAlteraMorada.getText().toString();
        String ConteudoContactoDoente = editTextAlteraContacto.getText().toString();
        String ConteudoDataNascimentoUtente = editTextAlteradNascimento.getText().toString();

        if (ConteudoNomeDoente.trim().isEmpty()){

            editTextAlteraNome.setError(getString(R.string.preencherNome));
            editTextAlteraNome.requestFocus();
            return;
        }

        if (ConteudoMoradaDoente.trim().isEmpty()) {

            editTextAlteraMorada.setError(getString(R.string.preencherMorada));
            editTextAlteraMorada.requestFocus();
            return;
        }

        if (ConteudoContactoDoente.trim().isEmpty()) {

            editTextAlteraContacto.setError(getString(R.string.preencherContacto));
            editTextAlteraContacto.requestFocus();
            return;
        }

        if (ConteudoDataNascimentoUtente.trim().isEmpty()) {

            editTextAlteradNascimento.setError(getString(R.string.Data_obrigatoria));
            editTextAlteradNascimento.requestFocus();
            return;
        }

        String nome = editTextAlteraNome.getText().toString();
        String morada = editTextAlteraMorada.getText().toString();
        String contacto = editTextAlteraContacto.getText().toString();
        String dNascimento = editTextAlteradNascimento.getText().toString();


        // Grava os dados do doente

        Doentes doentes = new Doentes();

        doentes.setNomeUtente(nome);
        doentes.setMoradaUtente(morada);
        doentes.setContactoUtente(contacto);
        doentes.setDataNascimentoUtente(dNascimento);


        try {
            getContentResolver().update( enderecoEditarDoentes, doentes.getContentValues(), null, null);
            Toast.makeText(this, (getString(R.string.doente_alterado)), Toast.LENGTH_SHORT).show();
            finish();

        } catch (Exception e) {
            Toast.makeText(this,(getString(R.string.erro_alterar_doente)), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
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

    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}