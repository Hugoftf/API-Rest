package com.github.Hugoftf.Spring.JPA.service;

import com.github.Hugoftf.Spring.JPA.controller.dto.AutorDTO;
import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.repository.AutorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AutorService {

    private AutorRepository autorRepository;

    public AutorService(AutorRepository autorRepository){
        this.autorRepository = autorRepository;
    }

    public Autor salvar(Autor autor){
        return autorRepository.save(autor);
    }

    public Optional<Autor> obterDetalhes(UUID id){
        return autorRepository.findById(id);
    }

    public void deletar(Autor autor) {
         autorRepository.delete(autor);
    }
}
