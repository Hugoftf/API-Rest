package com.github.Hugoftf.Spring.JPA.controller.dto;

import com.github.Hugoftf.Spring.JPA.model.GeneroLivro;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

public record ResultadoPesquisaLivroDTO(
        UUID id,
        String isbn,
        String titulo,
        LocalDate dataPublicacao,
        GeneroLivro generoLivro,
        BigDecimal preco,
        AutorDTO autor
) {
}
