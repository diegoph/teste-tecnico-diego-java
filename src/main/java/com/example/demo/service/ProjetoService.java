package com.example.demo.service;

import com.example.demo.enums.ProjetoStatus;
import com.example.demo.model.Projeto;
import com.example.demo.repository.ProjetoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ProjetoService {

    private static final Set<ProjetoStatus> exclusionProhibitedStatusList = Set.of(
            ProjetoStatus.INICIADO,
            ProjetoStatus.EM_ANDAMENTO,
            ProjetoStatus.ENCERRADO);

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Optional<Projeto> findById(Long id) {
        return projetoRepository.findById(id);
    }

    public Projeto save(Projeto projeto) {
        projeto.setStatus(ProjetoStatus.EM_ANALISE);
        return projetoRepository.save(projeto);
    }

    public Projeto update(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    public void delete(Long id) {
        Optional<Projeto> projetoOptional = projetoRepository.findById(id);
        if (projetoOptional.isPresent()) {
            Projeto projeto = projetoOptional.get();
            if (!exclusionProhibitedStatusList.contains(projeto.getStatus())) {
                projetoRepository.deleteById(id);
            }
        }
    }

}
