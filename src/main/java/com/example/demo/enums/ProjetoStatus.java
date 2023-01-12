package com.example.demo.enums;

import lombok.Getter;

@Getter
public enum ProjetoStatus {

    EM_ANALISE("Em Análise"),
    ANALISE_REALIZADA("Análise Realizada"),
    ANALISE_APROVADA("Análise Aprovada"),
    INICIADO("Iniciado"),
    PLANEJADO("Planejado"),
    EM_ANDAMENTO("Em Andamento"),
    ENCERRADO("Encerrado"),
    CANCELADO("Cancelado");

    private final String descricao;

    ProjetoStatus(String descricao) {
        this.descricao = descricao;
    }

}
