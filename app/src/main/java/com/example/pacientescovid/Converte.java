package com.example.pacientescovid;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {

    public static ContentValues sintomasToContentValues(Sintomas sintomas) {
        ContentValues valores = new ContentValues();

        valores.put(BdTableSintomas.CAMPO_SINTOMAS, sintomas.getSintoma());
        valores.put(BdTableSintomas.CAMPO_DESCRICAO_SINTOMAS, sintomas.getDescricaoSintoma());

        return valores;
    }

    public static Sintomas contentValuesToSintomas(ContentValues valores) {
        Sintomas sintomas = new Sintomas();

        sintomas.setId(valores.getAsLong(BdTableSintomas._ID));
        sintomas.setSintoma(valores.getAsString(BdTableSintomas.CAMPO_SINTOMAS));
        sintomas.setDescricaoSintoma(valores.getAsString(BdTableSintomas.CAMPO_DESCRICAO_SINTOMAS));

        return sintomas;
    }

    public static ContentValues doentesToContentValues(Doentes doentes) {
        ContentValues valores = new ContentValues();

        valores.put(BdTableDoentes.CAMPO_NOME, doentes.getNomeUtente());
        valores.put(BdTableDoentes.CAMPO_MORADA, doentes.getMoradaUtente());
        valores.put(BdTableDoentes.CAMPO_CONTACTO, doentes.getContactoUtente());
        valores.put(BdTableDoentes.CAMPO_DATA_NASCIMENTO, doentes.getDataNascimentoUtente());

        return valores;
    }

    public static Doentes contentValuesToDoentes(ContentValues valores) {
        Doentes doentes = new Doentes();

        doentes.setId(valores.getAsLong(BdTableDoentes._ID));
        doentes.setNomeUtente(valores.getAsString(BdTableDoentes.CAMPO_NOME));
        doentes.setMoradaUtente(valores.getAsString(BdTableDoentes.CAMPO_MORADA));
        doentes.setContactoUtente(valores.getAsString(BdTableDoentes.CAMPO_CONTACTO));
        doentes.setDataNascimentoUtente(valores.getAsString(BdTableDoentes.CAMPO_DATA_NASCIMENTO));

        return doentes;
    }


    public static ContentValues estadosaudeToContentValues(EstadoSaude estado) {
        ContentValues valores = new ContentValues();

        valores.put(BdTableEstadoSaude.CAMPO_HORA_VISITA, estado.getHoraVisita());
        valores.put(BdTableEstadoSaude.CAMPO_DIA_VISITA, estado.getDiaVisita());
        valores.put(BdTableEstadoSaude.CAMPO_TEMPERATURA, estado.getTemperatura());
        valores.put(BdTableEstadoSaude.CAMPO_MEDICAMENTOS, estado.getMedicamentos());

        valores.put(BdTableEstadoSaude.CAMPO_DOENTE, estado.getDoente());
        valores.put(BdTableEstadoSaude.CAMPO_ID_DOENTE, estado.getIdDoente());

        return valores;
    }

    public static EstadoSaude contentValuesToEstadoSaude(ContentValues valores) {
        EstadoSaude estado = new EstadoSaude();

        estado.setId(valores.getAsLong(BdTableEstadoSaude._ID));
        estado.setHoraVisita(valores.getAsString(BdTableEstadoSaude.CAMPO_HORA_VISITA));
        estado.setDiaVisita(valores.getAsString(BdTableEstadoSaude.CAMPO_DIA_VISITA));
        estado.setTemperatura(valores.getAsString(BdTableEstadoSaude.CAMPO_TEMPERATURA));
        estado.setMedicamentos(valores.getAsString(BdTableEstadoSaude.CAMPO_MEDICAMENTOS));

        estado.setDoente(valores.getAsString(BdTableEstadoSaude.CAMPO_DOENTE));
        estado.setIdDoente(valores.getAsLong(BdTableEstadoSaude.CAMPO_ID_DOENTE));

        return estado;
    }



/*
    public static Livro cursorToLivro(Cursor cursor) {
        Livro livro = new Livro();

        livro.setId(cursor.getLong(cursor.getColumnIndex(BdTableLivros._ID)));
        livro.setTitulo(cursor.getString(cursor.getColumnIndex(BdTableLivros.CAMPO_TITULO)));
        livro.setIdCategoria(cursor.getLong(cursor.getColumnIndex(BdTableLivros.CAMPO_ID_CATEGORIA)));
        livro.setCategoria(cursor.getString(cursor.getColumnIndex(BdTableLivros.CAMPO_CATEGORIA)));

        return livro;
    }
*/

}
