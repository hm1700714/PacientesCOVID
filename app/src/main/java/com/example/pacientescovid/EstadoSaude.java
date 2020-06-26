package com.example.pacientescovid;

public class EstadoSaude {
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

        valores.put(BdTableDoentes.CAMPO_NOME, nomeUtente);
        valores.put(BdTableDoentes.CAMPO_NOME, nomeUtente);
        valores.put(BdTableDoentes.CAMPO_MORADA, moradaUtente);
        valores.put(BdTableDoentes.CAMPO_CONTACTO, contactoUtente);
        valores.put(BdTableDoentes.CAMPO_DATA_NASCIMENTO, dataNascimentoUtente);

        return valores;
    }
     */


    /*

        public static Doentes fromCursor(Cursor cursor){

        passar o id do doente e do estado de saude tbm
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

     */
}
