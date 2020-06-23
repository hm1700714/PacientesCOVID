package com.example.pacientescovid;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;

public class ActivityMostraDoentes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private AdaptadorDoentes adaptadorDoentes;
    private RecyclerView recyclerViewDoentes;

    public static final int ID_CURSOR_LOADER_DOENTES = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_doentes);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_DOENTES, null, this);
        recyclerViewDoentes = (RecyclerView) findViewById(R.id.recyclerViewSintomas);
        adaptadorDoentes = new AdaptadorDoentes(this);
        recyclerViewDoentes.setAdapter(adaptadorDoentes);
        recyclerViewDoentes.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adaptadorDoentes.setCursor(null);

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_DOENTES, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, PacientesContentProvider.ENDERECO_DOENTES,
                BdTableDoentes.TODOS_CAMPOS, null, null, BdTableDoentes.CAMPO_NOME);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorDoentes.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorDoentes.setCursor(null);
    }
}