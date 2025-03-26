package com.github.Hugoftf.Spring.JPA.controller.mappers;

import com.github.Hugoftf.Spring.JPA.controller.dto.CadastroLivroDTO;
import com.github.Hugoftf.Spring.JPA.model.Livro;
import com.github.Hugoftf.Spring.JPA.repository.AutorRepository;
import com.github.Hugoftf.Spring.JPA.repository.LivroRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = "spring")
public abstract class LivroMapper {

    @Autowired
    AutorRepository autorRepository;

    @Mapping(target = "idAutor", expression = "java( autorRepository.findById(dto.idAutor()).orElse(null) )")
    public abstract Livro toEntity(CadastroLivroDTO dto);
}
