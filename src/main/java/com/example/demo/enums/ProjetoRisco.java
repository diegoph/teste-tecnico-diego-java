package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ProjetoRisco {

    BAIXO("Baixo Risco"),
    MEDIO("Médio Risco"),
    ALTO("Alto Risco");

    private final String descricao;

    ProjetoRisco(String descricao) {
        this.descricao = descricao;
    }
}
