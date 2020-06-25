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

import java.io.Serializable;

public class ActivityMostraEstado extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    public static final int ID_CURSOR_LOADER_DOENTE = 0;

    public static final String ID_DOENTE = "ID_DOENTE";
    public static final String ID_Estado = "ID_Estado";

    private AdaptadorEstado adaptadorEstado;
    private RecyclerView recyclerViewEstado;
    private Menu menu;

    private EstadoSaude estadoSaude = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_estado);


        recyclerViewEstado = (RecyclerView) findViewById(R.id.recyclerViewEstado);
        adaptadorEstado = new AdaptadorEstado(this);
        recyclerViewEstado.setAdapter(adaptadorEstado);
        recyclerViewEstado.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        adaptadorEstado.setCursor(null);

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_DOENTE, null, this);

    }


    public void atualizaOpcoesMenu() {
        EstadoSaude estado = adaptadorEstado.getEstadoSelecionado();

        boolean mostraAlterarEliminar = (estado != null);
        menu.findItem(R.id.action_moreEdit).setVisible(mostraAlterarEliminar);
        menu.findItem(R.id.action_moreDelete).setVisible(mostraAlterarEliminar);

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        this.menu = menu;
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.action_moreEdit) {

            Intent intent = new Intent(this, ActivityAlteraEstado.class);

            intent.putExtra("Estado", (Serializable) estadoSaude);

            startActivity(intent);

        }else if(id == R.id.action_moreDelete) {
            /*
            Intent intent = new Intent(this, ActivityEliminarSintomas.class);

            intent.putExtra(ID_SINTOMAS, adaptadorEstado.getEstadoSelecionado().getId());

            startActivity(intent);

             */
        }
        return super.onOptionsItemSelected(item);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, PacientesContentProvider.ENDERECO_ESTADOSAUDE,
                BdTableEstadoSaude.TODOS_CAMPOS, null, null, BdTableEstadoSaude.CAMPO_ID_DOENTE);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        adaptadorEstado.setCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        adaptadorEstado.setCursor(null);
    }
}