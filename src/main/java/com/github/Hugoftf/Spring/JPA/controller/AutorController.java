package com.github.Hugoftf.Spring.JPA.controller;


import com.github.Hugoftf.Spring.JPA.controller.dto.AutorDTO;
import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("autores")
public class AutorController {

    public AutorService autorService;

    public AutorController(AutorService autorService){
        this.autorService = autorService;
    }


    @PostMapping
    public ResponseEntity<Void> salvar(@RequestBody AutorDTO autorDTO){

        Autor autorMapeado = autorDTO.mapeandoParaAutor();
        autorService.salvar(autorMapeado);

        //http://localhost:8080/autores/(ID AQUI)
       URI uri =  ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(autorMapeado.getId())
                .toUri();

        return  ResponseEntity.created(uri).build();
    }
}
