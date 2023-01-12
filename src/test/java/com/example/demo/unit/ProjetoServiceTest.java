package com.example.demo.unit;

import com.example.demo.model.Pessoa;
import com.example.demo.model.Projeto;
import com.example.demo.repository.ProjetoRepository;
import com.example.demo.service.ProjetoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ProjetoServiceTest {

    @Mock
    private ProjetoRepository projetoRepository;

    @InjectMocks
    private ProjetoService projetoService;

    @Test
    void deveCadastrarProjetoComSucesso() {
        Projeto projeto = new Projeto();
        projeto.setNome("Diego Test");
        projeto.setGerente(new Pessoa(1L));
        projetoService.save(projeto);
        verify(projetoRepository).save(any(Projeto.class));
    }

}
