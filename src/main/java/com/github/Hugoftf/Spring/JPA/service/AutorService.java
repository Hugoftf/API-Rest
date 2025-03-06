package com.github.Hugoftf.Spring.JPA.service;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.repository.AutorRepository;
import org.springframework.stereotype.Service;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }



}
