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
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class ActivityMostraDoentes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final String ID_DOENTES = "ID_DOENTES";

    private AdaptadorDoentes adaptadorDoentes;
    private RecyclerView recyclerViewDoentes;
    private Menu menu;

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

    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_DOENTES, null, this);
        super.onResume();
    }


    public void atualizaOpcoesMenu() {
        Doentes pessoasModel = adaptadorDoentes.getDoenteSelecionado();

        boolean mostraAlterarEliminar = (pessoasModel != null);
        menu.findItem(R.id.action_moreEdit).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_moreDelete).setVisible(mostraAlterarEliminar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        this.menu = menu;
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_moreEdit) {
           Intent intent = new Intent(this, ActivityAlteraDoentes.class);

           intent.putExtra(ID_DOENTES, adaptadorDoentes.getDoenteSelecionado().getId());

           startActivity(intent);
        }else if(id == R.id.action_moreDelete) {
            Intent intent = new Intent(this, ActivityEliminarDoente.class);

            intent.putExtra(ID_DOENTES, adaptadorDoentes.getDoenteSelecionado().getId());

            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
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