package com.example.moneymind.enums;

public enum TypeFinancialExpense {

    NON_ESSENTIAL("Non essential"),
    ESSENTIAL("Essential");

    private final String description;

    TypeFinancialExpense(String description) {
        this.description = description;
    }
}
