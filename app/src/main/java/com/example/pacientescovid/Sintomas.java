package com.example.pacientescovid;

import android.content.ContentValues;
import android.database.Cursor;

public class Sintomas {
    private long id = -1;
    private String Sintoma;
    private String descricaoSintoma;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSintoma() {
        return Sintoma;
    }

    public void setSintoma(String sintoma) {
        Sintoma = sintoma;
    }

    public String getDescricaoSintoma() {
        return descricaoSintoma;
    }

    public void setDescricaoSintoma(String descricaoSintoma) {
        this.descricaoSintoma = descricaoSintoma;
    }

    public static Sintomas fromCursor(Cursor cursor){

        long id = cursor.getLong(
                cursor.getColumnIndex(BdTableSintomas._ID)
        );
        String sintoma = cursor.getString(
                cursor.getColumnIndex(BdTableSintomas.CAMPO_SINTOMAS)
        );
        String descSintoma = cursor.getString(
                cursor.getColumnIndex(BdTableSintomas.CAMPO_DESCRICAO_SINTOMAS)
        );

        Sintomas sintomas = new Sintomas();

        sintomas.setId(id);
        sintomas.setSintoma(sintoma);
        sintomas.setDescricaoSintoma(descSintoma);

        return sintomas;
    }

    public ContentValues getContentValues(){
        ContentValues valores = new ContentValues();

        valores.put(BdTableSintomas.CAMPO_SINTOMAS, Sintoma);
        valores.put(BdTableSintomas.CAMPO_DESCRICAO_SINTOMAS, descricaoSintoma);

        return valores;
    }

}
