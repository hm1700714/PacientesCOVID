package com.example.pacientescovid;

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
}
