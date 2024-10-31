package com.example.moneymind.enums;

public enum TipoDespesa {

    NAO_ESSENCIAL("Não essencial"),
    ESSENCIAL("Essencial");

    private final String descricao;

    TipoDespesa(String descricao) {
        this.descricao = descricao;
    }
}
