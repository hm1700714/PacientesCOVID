package com.example.pacientescovid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class BdAppTest {
    @Before
    @After
    public void apagaBaseDados() {
        getTargetContext().deleteDatabase(BdAppOpenHelper.NOME_BASE_DADOS);
    }

    @Test
    public void consegueAbrirBaseDados() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdApp = openHelper.getReadableDatabase();
        assertTrue(bdApp.isOpen());
        bdApp.close();
    }

    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private long insereSintomas(BdTableSintomas tabelaSintomas, Sintomas sintomas) {
        long id = tabelaSintomas.insert(Converte.sintomasToContentValues(sintomas));
        assertNotEquals(-1, id);
        return id;
    }


    private long insereSintomas(BdTableSintomas tabelaSintomas, String sintoma, String descricao) {

        Sintomas sintomas = new Sintomas();
        sintomas.setSintoma(sintoma);
        sintomas.setDescricaoSintoma(descricao);

        return insereSintomas(tabelaSintomas, sintomas);
    }



    private long insereDoentes(BdTableDoentes tabelaDoentes, Doentes doentes) {
        long id = tabelaDoentes.insert(Converte.doentesToContentValues(doentes));
        assertNotEquals(-1, id);
        return id;
    }


    private long insereDoentes(BdTableDoentes tabelaDoentes, String nome, String morada, String contacto, String dnascimento ) {

        Doentes doentes = new Doentes();
        doentes.setNomeUtente(nome);
        doentes.setMoradaUtente(morada);
        doentes.setContactoUtente(contacto);
        doentes.setDataNascimentoUtente(dnascimento);

        return insereDoentes(tabelaDoentes, doentes);
    }


    private long insereEstadoSaude(SQLiteDatabase bdPacientes, String hora, String dia, String temperatura, String medicamentos,
                                   String nome, String morada, String contacto, String dnascimento) {

        BdTableDoentes tabelaDoentes = new BdTableDoentes(bdPacientes);

        long idDoentes = insereDoentes(tabelaDoentes, nome, morada, contacto, dnascimento);

        EstadoSaude estado = new EstadoSaude();
        estado.setHoraVisita(hora);
        estado.setDiaVisita(dia);
        estado.setTemperatura(temperatura);
        estado.setMedicamentos(medicamentos);
        estado.setIdDoente(idDoentes);

        BdTableEstadoSaude tabelaEstados = new BdTableEstadoSaude(bdPacientes);
        long id = tabelaEstados.insert(Converte.estadosaudeToContentValues(estado));
        assertNotEquals(-1, id);

        return  id;
    }
    


    @Test
    public void consegueInserirCategorias() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdLivros);

        insereCategoria(tabelaCategorias, "Ação");

        bdLivros.close();
    }

    @Test
    public void consegueLerCategorias() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdLivros);

        Cursor cursor = tabelaCategorias.query(BdTableCategorias.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereCategoria(tabelaCategorias, "Sci-fi");

        cursor = tabelaCategorias.query(BdTableCategorias.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdLivros.close();
    }

    @Test
    public void consegueAlterarCategorias() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdLivros);

        Categoria categoria = new Categoria();
        categoria.setDescricao("Romanc");

        long id = insereCategoria(tabelaCategorias, categoria);

        categoria.setDescricao("Romance");
        int registosAfetados = tabelaCategorias.update(Converte.categoriaToContentValues(categoria), BdTableCategorias._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAfetados);

        bdLivros.close();
    }

    @Test
    public void consegueEliminarCategorias() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        BdTableCategorias tabelaCategorias = new BdTableCategorias(bdLivros);

        long id = insereCategoria(tabelaCategorias, "TESTE");

        int registosEliminados = tabelaCategorias.delete(BdTableCategorias._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdLivros.close();
    }

    @Test
    public void consegueInserirLivros() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        insereLivro(bdLivros, "O Intruso", "Terror");

        bdLivros.close();
    }

    @Test
    public void consegueLerLivros() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        BdTableLivros tabelaLivros = new BdTableLivros(bdLivros);

        Cursor cursor = tabelaLivros.query(BdTableLivros.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereLivro(bdLivros, "O silêncio dos inocentes", "Thriller");

        cursor = tabelaLivros.query(BdTableLivros.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdLivros.close();
    }

    @Test
    public void consegueAlterarLivros() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        long idLivro = insereLivro(bdLivros, "O silêncio dos inocentes", "Thriller");

        BdTableLivros tabelaLivros = new BdTableLivros(bdLivros);

        Cursor cursor = tabelaLivros.query(BdTableLivros.TODOS_CAMPOS, BdTableLivros.CAMPO_ID_COMPLETO + "=?", new String[]{ String.valueOf(idLivro) }, null, null, null);
        assertEquals(1, cursor.getCount());

        assertTrue(cursor.moveToNext());
        Livro livro = Converte.cursorToLivro(cursor);
        cursor.close();

        assertEquals("O silêncio dos inocentes", livro.getTitulo());

        livro.setTitulo("O mistério do quarto secreto");
        int registosAfetados = tabelaLivros.update(Converte.livroToContentValues(livro), BdTableLivros.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(livro.getId())});
        assertEquals(1, registosAfetados);

        bdLivros.close();
    }

    @Test
    public void consegueEliminarLivros() {
        Context appContext = getTargetContext();

        BdLivrosOpenHelper openHelper = new BdLivrosOpenHelper(appContext);
        SQLiteDatabase bdLivros = openHelper.getWritableDatabase();

        long id = insereLivro(bdLivros, "O silêncio dos inocentes", "Thriller");

        BdTableLivros tabelaLivros = new BdTableLivros(bdLivros);
        int registosEliminados = tabelaLivros.delete(BdTableLivros._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdLivros.close();
    }
}