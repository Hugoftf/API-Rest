package com.github.Hugoftf.Spring.JPA.controller.dto;

import com.github.Hugoftf.Spring.JPA.model.GeneroLivro;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroLivroDTO(
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        UUID idAutor
        ) {
}
