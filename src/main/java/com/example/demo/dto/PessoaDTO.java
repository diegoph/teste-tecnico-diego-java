package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaDTO {

    private String nome;
    private boolean isFuncionario;

    public boolean getIsFuncionario() {
        return isFuncionario;
    }

    public void setIsFuncionario(boolean isFuncionario) {
        this.isFuncionario = isFuncionario;
    }

}
