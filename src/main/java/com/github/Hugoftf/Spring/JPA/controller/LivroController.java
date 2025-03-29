package com.github.Hugoftf.Spring.JPA.controller;

import com.github.Hugoftf.Spring.JPA.controller.dto.CadastroLivroDTO;
import com.github.Hugoftf.Spring.JPA.controller.dto.ErroResposta;
import com.github.Hugoftf.Spring.JPA.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.Hugoftf.Spring.JPA.controller.mappers.LivroMapper;
import com.github.Hugoftf.Spring.JPA.exceptions.OperacaoNaoPermitida;
import com.github.Hugoftf.Spring.JPA.model.GeneroLivro;
import com.github.Hugoftf.Spring.JPA.model.Livro;
import com.github.Hugoftf.Spring.JPA.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/livros")
public class LivroController implements GenericController {

    private final LivroService livroService;
    private final LivroMapper livroMapper;

    public LivroController(LivroService livroService, LivroMapper livroMapper){
        this.livroService = livroService;
        this.livroMapper = livroMapper;
    }

    @PostMapping
    public ResponseEntity<Void> salvarLivro(@RequestBody @Valid CadastroLivroDTO livroDTO){

        Livro livro = livroMapper.toEntity(livroDTO);
        livroService.salvarLivro(livro);
        var url = gerarHearderLocation(livro.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultadoPesquisaLivroDTO> obterPorId(@PathVariable("id") String id){
        return livroService.obterPorId(UUID.fromString(id))
                .map(livro -> {
                    var dto = livroMapper.toDTO(livro);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletarLivro(@PathVariable("id") String id){
        return livroService.obterPorId(UUID.fromString(id)).map( livro -> {
            livroService.deletarLivro(livro);
            return ResponseEntity.ok().build();
        }).orElseGet(()-> ResponseEntity.notFound().build());
    }


    @GetMapping
    public ResponseEntity<List<ResultadoPesquisaLivroDTO>> pesquisar(
            @RequestParam(value = "isbn", required = false) String isbn,
            @RequestParam(value = "titulo", required = false) String titulo,
            @RequestParam(value = "genero", required = false) GeneroLivro genero)
             {

        var livro = livroService.pesquisa(isbn, titulo,genero);
        var lista = livro.stream().map(livroMapper::toDTO).collect(Collectors.toList());

        return ResponseEntity.ok(lista);
    }


}
