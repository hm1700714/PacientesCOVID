package com.example.pacientescovid;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

public class EstadoSaude implements Serializable {
    private long id = -1;
    private String horaVisita;
    private String diaVisita;
    private String Temperatura;
    private String Medicamentos;
    private long idDoente = -1;
    private String Doente = null;

    public long getIdDoente() {
        return idDoente;
    }

    public void setIdDoente(long idDoente) {
        this.idDoente = idDoente;
    }

    public String getDoente() {
        return Doente;
    }

    public void setDoente(String doente) {
        Doente = doente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHoraVisita() {
        return horaVisita;
    }

    public void setHoraVisita(String horaVisita) {
        this.horaVisita = horaVisita;
    }

    public String getDiaVisita() {
        return diaVisita;
    }

    public void setDiaVisita(String diaVisita) {
        this.diaVisita = diaVisita;
    }

    public String getTemperatura() {
        return Temperatura;
    }

    public void setTemperatura(String temperatura) {
        Temperatura = temperatura;
    }
    public String getMedicamentos() {
        return Medicamentos;
    }

    public void setMedicamentos(String medicamentos) {
        Medicamentos = medicamentos;
    }

/*
    public ContentValues getContentValues(){
    ContentValues valores = new ContentValues();

    valores.put(BdTableEstadoSaude.CAMPO_DOENTE, Doente);
    valores.put(BdTableEstadoSaude.CAMPO_HORA_VISITA, horaVisita);
    valores.put(BdTableEstadoSaude.CAMPO_DIA_VISITA, diaVisita);
    valores.put(BdTableEstadoSaude.CAMPO_TEMPERATURA, Temperatura);
    valores.put(BdTableEstadoSaude.CAMPO_MEDICAMENTOS, Medicamentos);

    return valores;
    }

    public static EstadoSaude fromCursor(Cursor cursor){

    //passar o id do doente e do estado de saude tbm

    long id = cursor.getLong(
             cursor.getColumnIndex(BdTableEstadoSaude._ID)
    );
    long idDoente = cursor.getLong(
                cursor.getColumnIndex(BdTableEstadoSaude.CAMPO_ID_DOENTE)
    );
    String hora = cursor.getString(
             cursor.getColumnIndex(BdTableEstadoSaude.CAMPO_HORA_VISITA)
    );
    String dia = cursor.getString(
             cursor.getColumnIndex(BdTableEstadoSaude.CAMPO_DIA_VISITA)
    );
    String temperatura = cursor.getString(
             cursor.getColumnIndex(BdTableEstadoSaude.CAMPO_TEMPERATURA)
    );
    String medicamentos = cursor.getString(
             cursor.getColumnIndex(BdTableEstadoSaude.CAMPO_MEDICAMENTOS)
    );

    EstadoSaude estado = new EstadoSaude();

        estado.setId(id);
        estado.setIdDoente(idDoente);
        estado.setHoraVisita(hora);
        estado.setDiaVisita(dia);
        estado.setTemperatura(temperatura);
        estado.setMedicamentos(medicamentos);

    return estado;
    }
    */

}
