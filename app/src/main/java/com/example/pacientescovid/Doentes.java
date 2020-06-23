package com.example.pacientescovid;

import android.content.ContentValues;
import android.database.Cursor;

public class Doentes {
    private long id = -1;
    private String nomeUtente;
    private String moradaUtente;
    private String contactoUtente;
    private String dataNascimentoUtente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeUtente() {
        return nomeUtente;
    }

    public void setNomeUtente(String nomeUtente) {
        this.nomeUtente = nomeUtente;
    }

    public String getMoradaUtente() {
        return moradaUtente;
    }

    public void setMoradaUtente(String moradaUtente) {
        this.moradaUtente = moradaUtente;
    }

    public String getContactoUtente() {
        return contactoUtente;
    }

    public void setContactoUtente(String contactoUtente) {
        this.contactoUtente = contactoUtente;
    }

    public String getDataNascimentoUtente() {
        return dataNascimentoUtente;
    }

    public void setDataNascimentoUtente(String dataNascimentoUtente) {
        this.dataNascimentoUtente = dataNascimentoUtente;
    }

    public static Doentes fromCursor(Cursor cursor){

        long id = cursor.getLong(
                cursor.getColumnIndex(BdTableDoentes._ID)
        );
        String nome = cursor.getString(
                cursor.getColumnIndex(BdTableDoentes.CAMPO_NOME)
        );
        String morada = cursor.getString(
                cursor.getColumnIndex(BdTableDoentes.CAMPO_MORADA)
        );
        String contacto = cursor.getString(
                cursor.getColumnIndex(BdTableDoentes.CAMPO_CONTACTO)
        );
        String dNascimento = cursor.getString(
                cursor.getColumnIndex(BdTableDoentes.CAMPO_DATA_NASCIMENTO)
        );

        Doentes doentes = new Doentes();

        doentes.setId(id);
        doentes.setNomeUtente(nome);
        doentes.setMoradaUtente(morada);
        doentes.setContactoUtente(contacto);
        doentes.setDataNascimentoUtente(dNascimento);

        return doentes;
    }

    public ContentValues getContentValues(){
        ContentValues valores = new ContentValues();

        valores.put(BdTableDoentes.CAMPO_NOME, nomeUtente);
        valores.put(BdTableDoentes.CAMPO_MORADA, moradaUtente);
        valores.put(BdTableDoentes.CAMPO_CONTACTO, contactoUtente);
        valores.put(BdTableDoentes.CAMPO_DATA_NASCIMENTO, dataNascimentoUtente);

        return valores;
    }
}
