package com.example.demo.controller;

import com.example.demo.enums.ProjetoRisco;
import com.example.demo.enums.ProjetoStatus;
import com.example.demo.model.Pessoa;
import com.example.demo.model.Projeto;
import com.example.demo.service.PessoaService;
import com.example.demo.service.ProjetoService;
import com.example.demo.utils.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequestMapping({"", "/projetos"})
public class ProjetoController {

    private static final String DEFAULT_PAGE = "projetos";

    private final ProjetoService projetoService;
    private final PessoaService pessoaService;

    public ProjetoController(ProjetoService projetoService, PessoaService pessoaService) {
        this.projetoService = projetoService;
        this.pessoaService = pessoaService;
    }

    @GetMapping
    public String view(Model model) {
        model.addAttribute("projetos", projetoService.findAll());
        model.addAttribute("funcionarios", pessoaService.findAllFuncionario());
        model.addAttribute("statuses", ProjetoStatus.values());
        model.addAttribute("riscos", ProjetoRisco.values());
        return DEFAULT_PAGE;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projeto> viewProjeto(@PathVariable Long id) {
        Optional<Projeto> projeto = projetoService.findById(id);
        return ResponseEntity.of(projeto);
    }

    @PostMapping("/create")
    public String create(HttpServletRequest request) {
        Projeto projeto = new Projeto();
        projeto.setNome(request.getParameter("nome"));
        projeto.setGerente(new Pessoa(Long.parseLong(request.getParameter("responsavel"))));

        if (!StringUtils.isBlankString(request.getParameter("data_inicio"))) {
            projeto.setDataInicio(LocalDate.parse(request.getParameter("data_inicio")));
        }
        if (!StringUtils.isBlankString(request.getParameter("data_previsao_fim"))) {
            projeto.setDataPrevisaoFim(LocalDate.parse(request.getParameter("data_previsao_fim")));
        }
        if (!StringUtils.isBlankString(request.getParameter("orcamento"))) {
            projeto.setOrcamento(StringUtils.formataStringRealParaDouble(request.getParameter("orcamento")));
        }

        projeto.setDescricao(request.getParameter("descricao"));
        projetoService.save(projeto);
        return "redirect:/" + DEFAULT_PAGE;
    }

    @PostMapping("/update")
    public String update(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        Projeto projeto = projetoService.findById(id).orElseThrow();

        projeto.setNome(request.getParameter("nome"));
        projeto.setGerente(new Pessoa(Long.parseLong(request.getParameter("responsavel"))));
        if (!StringUtils.isBlankString(request.getParameter("data_inicio"))) {
            projeto.setDataInicio(LocalDate.parse(request.getParameter("data_inicio")));
        } else {
            projeto.setDataInicio(null);
        }
        if (!StringUtils.isBlankString(request.getParameter("data_previsao_fim"))) {
            projeto.setDataPrevisaoFim(LocalDate.parse(request.getParameter("data_previsao_fim")));
        } else {
            projeto.setDataPrevisaoFim(null);
        }
        projeto.setDescricao(request.getParameter("descricao"));
        if (!StringUtils.isBlankString(request.getParameter("orcamento"))) {
            projeto.setOrcamento(StringUtils.formataStringRealParaDouble(request.getParameter("orcamento")));
        } else {
            projeto.setOrcamento(null);
        }
        projeto.setDescricao(request.getParameter("descricao"));
        if (!StringUtils.isBlankString(request.getParameter("data_fim"))) {
            projeto.setDataFim(LocalDate.parse(request.getParameter("data_fim")));
        } else {
            projeto.setDataFim(null);
        }
        projeto.setStatus(ProjetoStatus.valueOf(request.getParameter("status")));
        if (!StringUtils.isBlankString(request.getParameter("risco"))) {
            projeto.setRisco(ProjetoRisco.valueOf(request.getParameter("risco")));
        } else {
            projeto.setRisco(null);
        }
        projeto.setDescricao(request.getParameter("descricao"));

        projetoService.update(projeto);

        return "redirect:/" + DEFAULT_PAGE;
    }

    @PostMapping("/delete")
    public String delete(HttpServletRequest request) {
        Long id = Long.parseLong(request.getParameter("id"));
        projetoService.delete(id);
        return "redirect:/" + DEFAULT_PAGE;
    }
}
