package com.example.pacientescovid;

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
}
