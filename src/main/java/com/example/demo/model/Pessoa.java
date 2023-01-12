package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column
    private String cpf;

    @Column(name = "datanascimento")
    private LocalDate dataNascimento;

    @Column(name = "funcionario")
    private boolean isFuncionario;

    @ManyToMany
    @JoinTable(
            name = "membros",
            joinColumns = @JoinColumn(name = "idpessoa"),
            inverseJoinColumns = @JoinColumn(name = "idprojeto"))
    private Set<Projeto> projetos;

    public void addProjeto(Projeto projeto) {
        this.projetos.add(projeto);
    }

    public Pessoa() {
    }

    public Pessoa(Long id) {
        this.id = id;
    }
}
