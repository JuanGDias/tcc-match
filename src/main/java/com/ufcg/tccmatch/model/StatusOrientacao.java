package com.ufcg.tccmatch.model;

public enum StatusOrientacao {
    EM_ANDAMENTO("Em andamento"),
    FINALIZADO("Finalizado");

    public String value;

    StatusOrientacao(String value) {
        this.value = value;
    }
}
