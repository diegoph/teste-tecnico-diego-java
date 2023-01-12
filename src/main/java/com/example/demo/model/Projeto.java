package com.example.demo.model;

import com.example.demo.enums.ProjetoRisco;
import com.example.demo.enums.ProjetoStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Setter
@Getter
@Entity
public class Projeto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nome;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_previsao_fim")
    private LocalDate dataPrevisaoFim;

    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column
    private ProjetoStatus status;

    @Column
    private Double orcamento;

    @Enumerated(EnumType.STRING)
    @Column
    private ProjetoRisco risco;

    @ManyToOne
    @JoinColumn(name = "idgerente")
    private Pessoa gerente;

    @ManyToMany(mappedBy = "projetos")
    private Set<Pessoa> membros;

    public Projeto() {
    }

    public Projeto(Long id) {
        this.id = id;
    }
}
