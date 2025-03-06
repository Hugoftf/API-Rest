package com.github.Hugoftf.Spring.JPA.controller.dto;

import com.github.Hugoftf.Spring.JPA.model.Autor;

import java.time.LocalDate;

public record AutorDTO(String nome,
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
