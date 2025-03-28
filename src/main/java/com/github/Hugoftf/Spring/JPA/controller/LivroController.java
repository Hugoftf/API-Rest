package com.github.Hugoftf.Spring.JPA.controller;

import com.github.Hugoftf.Spring.JPA.controller.dto.CadastroLivroDTO;
import com.github.Hugoftf.Spring.JPA.controller.dto.ErroResposta;
import com.github.Hugoftf.Spring.JPA.controller.dto.ResultadoPesquisaLivroDTO;
import com.github.Hugoftf.Spring.JPA.controller.mappers.LivroMapper;
import com.github.Hugoftf.Spring.JPA.exceptions.OperacaoNaoPermitida;
import com.github.Hugoftf.Spring.JPA.model.Livro;
import com.github.Hugoftf.Spring.JPA.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

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


}
