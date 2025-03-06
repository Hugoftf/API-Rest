package com.github.Hugoftf.Spring.JPA.controller.dto;

import com.github.Hugoftf.Spring.JPA.model.Autor;

import java.time.LocalDate;
import java.util.UUID;

public record AutorDTO( UUID id,
                        String nome,
                       LocalDate dataNascimento,
                       String nacionalidade) {


    public Autor mapeandoParaAutor(){
        Autor autor =  new Autor();

        autor.setNome(this.nome);
        autor.setDataNascimento(this.dataNascimento);
        autor.setNacionalidade(this.nacionalidade);

        return autor;
    }

}
