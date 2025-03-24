package com.github.Hugoftf.Spring.JPA.service;

import com.github.Hugoftf.Spring.JPA.repository.LivroRepository;
import org.springframework.stereotype.Service;

@Service
public class LivroService {

    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository){
        this.livroRepository = livroRepository;
    }

}
