package com.example.pacientescovid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class ActivityMostraSintomas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ID_SINTOMAS = "ID_SINTOMAS";

    private AdaptadorSintomas adaptadorSintomas;
    private RecyclerView recyclerViewSintomas;
    private Menu menu;

    public static final int ID_CURSOR_LOADER_SINTOMAS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_sintomas);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_SINTOMAS, null, this);
        recyclerViewSintomas = (RecyclerView) findViewById(R.id.recyclerViewSintomas);
        adaptadorSintomas = new AdaptadorSintomas(this);
        recyclerViewSintomas.setAdapter(adaptadorSintomas);
        recyclerViewSintomas.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adaptadorSintomas.setCursor(null);

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_SINTOMAS, null, this);
    }

    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_SINTOMAS, null, this);
        super.onResume();
    }


    public void atualizaOpcoesMenu() {
        Sintomas sintomas = adaptadorSintomas.getSintomaSelecionado();

        boolean mostraAlterarEliminar = (sintomas != null);
        menu.findItem(R.id.action_moreEdit).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_moreDelete).setVisible(mostraAlterarEliminar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        this.menu = menu;
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_moreEdit) {
            Intent intent = new Intent(this, ActivityAlteraSintomas.class);

            intent.putExtra(ID_SINTOMAS, adaptadorSintomas.getSintomaSelecionado().getId());

            startActivity(intent);
        }else if(id == R.id.action_moreDelete) {
            Intent intent = new Intent(this, ActivityEliminarSintomas.class);

            intent.putExtra(ID_SINTOMAS, adaptadorSintomas.getSintomaSelecionado().getId());

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, PacientesContentProvider.ENDERECO_SINTOMAS,
                BdTableSintomas.TODOS_CAMPOS, null, null, BdTableSintomas.CAMPO_SINTOMAS);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorSintomas.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorSintomas.setCursor(null);
    }
}