package com.example.demo.controller;

import com.example.demo.dto.PessoaDTO;
import com.example.demo.model.Pessoa;
import com.example.demo.service.PessoaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {
    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @PostMapping
    public ResponseEntity<Pessoa> create(@RequestBody PessoaDTO dto) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(dto.getNome());
        pessoa.setFuncionario(dto.getIsFuncionario());
        pessoaService.save(pessoa);
        return ResponseEntity.ok(pessoa);
    }

    @PutMapping("/{idPessoa}/projeto/{idProjeto}")
    public ResponseEntity<Void> update(@PathVariable Long idPessoa, @PathVariable Long idProjeto) {
        pessoaService.savePessoaProjeto(idPessoa, idProjeto);
        return ResponseEntity.ok().build();
    }

}
