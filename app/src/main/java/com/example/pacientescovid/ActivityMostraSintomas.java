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

public class ActivityMostraSintomas extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {


    private AdaptadorSintomas adaptadorSintomas;
    private RecyclerView recyclerViewSintomas;

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