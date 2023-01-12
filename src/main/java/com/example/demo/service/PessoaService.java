package com.example.demo.service;

import com.example.demo.exception.NotFoundException;
import com.example.demo.model.Pessoa;
import com.example.demo.model.Projeto;
import com.example.demo.repository.PessoaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {

    private final PessoaRepository pessoaRepository;

    public PessoaService(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }

    public List<Pessoa> findAllFuncionario() {
        return pessoaRepository.findAllByIsFuncionarioTrue();
    }

    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public void savePessoaProjeto(Long idPessoa, Long idProjeto) {
        Pessoa pessoa = pessoaRepository.findById(idPessoa).orElseThrow(() -> new NotFoundException("Membro não existe"));
        pessoa.addProjeto(new Projeto(idProjeto));
        pessoaRepository.save(pessoa);
    }
}
