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
    public void consegueInserirSintomas() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableSintomas tabelaSintomas = new BdTableSintomas(bdPacientes);

        insereSintomas(tabelaSintomas, "Dores de Cabeça", "Fortes dores de cabeça, na parte do lobo frontal");

        bdPacientes.close();
    }


    @Test
    public void consegueLerSintomas() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableSintomas tabelaSintomas = new BdTableSintomas(bdPacientes);

        Cursor cursor = tabelaSintomas.query(BdTableSintomas.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereSintomas(tabelaSintomas, "Dores de Cabeça", "Fortes dores de cabeça, na parte do lobo frontal");

        cursor = tabelaSintomas.query(BdTableSintomas.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdPacientes.close();
    }


    @Test
    public void consegueAlterarSintomas() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableSintomas tabelaSintomas = new BdTableSintomas(bdPacientes);

        Sintomas sintomas = new Sintomas();
        sintomas.setSintoma("Dificuldades em respirar");
        sintomas.setDescricaoSintoma("Devido a ... o paciente tem dificuldades em manter a sua respiração estável");

        long id = insereSintomas(tabelaSintomas, sintomas);

        sintomas.setSintoma("Dificuldades");

        int registosAfetados = tabelaSintomas.update(Converte.sintomasToContentValues(sintomas), BdTableSintomas._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAfetados);

        bdPacientes.close();
    }


    @Test
    public void consegueEliminarSintomas() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableSintomas tabelaSintomas = new BdTableSintomas(bdPacientes);

        long id = insereSintomas(tabelaSintomas, "TESTE", "TESTE");

        int registosEliminados = tabelaSintomas.delete(BdTableSintomas._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdPacientes.close();
    }



    @Test
    public void consegueInserirDoentes() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableDoentes tabelaDoentes = new BdTableDoentes(bdPacientes);

        insereDoentes(tabelaDoentes, "Henrique Moreira", "Rua da Alegria, Guarda", "969696963","13/10/1996");

        bdPacientes.close();
    }


    @Test
    public void consegueLerDoentes() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableDoentes tabelaDoentes = new BdTableDoentes(bdPacientes);

        Cursor cursor = tabelaDoentes.query(BdTableDoentes.TODOS_CAMPOS, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();

        insereDoentes(tabelaDoentes, "Henrique Moreira", "Rua da Alegria, Guarda", "969696963", "13/10/1996");

        cursor = tabelaDoentes.query(BdTableDoentes.TODOS_CAMPOS, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        bdPacientes.close();
    }



    @Test
    public void consegueAlterarDoentes() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableDoentes tabelaDoentes = new BdTableDoentes(bdPacientes);

        long idLivro = insereDoentes(tabelaDoentes, "Henrique Moreira", "Rua da Alegria, Guarda", "969696963", "13/10/1996");

        Cursor cursor = tabelaDoentes.query(BdTableDoentes.TODOS_CAMPOS, BdTableDoentes.CAMPO_ID_COMPLETO + "=?",
                new String[]{ String.valueOf(idLivro) }, null, null, null);
        assertEquals(1, cursor.getCount());

        assertTrue(cursor.moveToNext());
        Doentes doentes = Converte.cursorToDoentes(cursor);
        cursor.close();

        assertEquals("Henrique Moreira", doentes.getNomeUtente());

        doentes.setNomeUtente("Gonçalo Moreira");
        int registosAfetados = tabelaDoentes.update(Converte.doentesToContentValues(doentes),
                BdTableDoentes.CAMPO_ID_COMPLETO + "=?",
                new String[]{String.valueOf(doentes.getId())});
        assertEquals(1, registosAfetados);

        bdPacientes.close();
    }

    @Test
    public void consegueEliminarDoentes() {
        Context appContext = getTargetContext();

        BdAppOpenHelper openHelper = new BdAppOpenHelper(appContext);
        SQLiteDatabase bdPacientes = openHelper.getWritableDatabase();

        BdTableDoentes tabelaDoentes = new BdTableDoentes(bdPacientes);

        long id = insereDoentes(tabelaDoentes, "Henrique Moreira", "Rua da Alegria, Guarda", "969696963", "13/10/1996");

        int registosEliminados = tabelaDoentes.delete(BdTableDoentes._ID + "=?",
                new String[]{String.valueOf(id)});
        assertEquals(1, registosEliminados);

        bdPacientes.close();

    }
/* 
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
*/

}