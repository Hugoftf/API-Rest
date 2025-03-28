package com.github.Hugoftf.Spring.JPA.service;

import com.github.Hugoftf.Spring.JPA.model.Livro;
import com.github.Hugoftf.Spring.JPA.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

    public Livro salvarLivro(Livro livro){
        return livroRepository.save(livro);
    }

    public Optional<Livro> obterPorId(UUID id){
        return livroRepository.findById(id);
    }


}
