package com.github.Hugoftf.Spring.JPA.service;

import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.model.GeneroLivro;
import com.github.Hugoftf.Spring.JPA.model.Livro;
import com.github.Hugoftf.Spring.JPA.repository.LivroRepository;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.support.ExampleMatcherAccessor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.github.Hugoftf.Spring.JPA.repository.specs.LivroSpecs.*;

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

    public void deletarLivro(Livro livro){
        livroRepository.delete(livro);
    }

    public List<Livro> pesquisa(String isbn, String titulo, GeneroLivro genero ) {

        Specification<Livro> specs = (root, query, cb) -> cb.conjunction();

        if (isbn != null){
            specs = specs.and(isbnEqual(isbn));
        }

        if (titulo != null){
            specs = specs.and(tituloLike(titulo));
        }

        if (genero != null){
            specs = specs.and(generoEqual(genero));
        }

        return livroRepository.findAll(specs);
    }

    public void atualizar(Livro livro){
        if (livro.getId() == null){
            throw new IllegalArgumentException("Para atualizar é necessario um livro");
        }
        livroRepository.save(livro);
    }
}
