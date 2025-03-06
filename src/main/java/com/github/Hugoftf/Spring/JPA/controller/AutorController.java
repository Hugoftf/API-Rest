package com.github.Hugoftf.Spring.JPA.controller;


import com.github.Hugoftf.Spring.JPA.controller.dto.AutorDTO;
import com.github.Hugoftf.Spring.JPA.model.Autor;
import com.github.Hugoftf.Spring.JPA.service.AutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> obterDetalhes(@PathVariable("id") String id){
        UUID idAutor = UUID.fromString(id);

        Optional<Autor> autorOptional = autorService.obterDetalhes(idAutor);
        if (autorOptional.isPresent()){
            Autor autor = autorOptional.get();

            AutorDTO autorDTO = new AutorDTO(autor.getId(),
                    autor.getNome(),
                    autor.getDataNascimento(),
                    autor.getNacionalidade());
            return ResponseEntity.ok(autorDTO);
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAutor(@PathVariable("id") String id){
        var idColetado = UUID.fromString(id);
        Optional<Autor> autorOptional = autorService.obterDetalhes(idColetado);

        if (autorOptional.isEmpty()){
            return ResponseEntity.notFound().build();
        }

        autorService.deletar(autorOptional.get());
        return ResponseEntity.noContent().build();

    }
}
