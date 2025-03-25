package com.github.Hugoftf.Spring.JPA.controller;

import com.github.Hugoftf.Spring.JPA.controller.dto.CadastroLivroDTO;
import com.github.Hugoftf.Spring.JPA.controller.dto.ErroResposta;
import com.github.Hugoftf.Spring.JPA.exceptions.OperacaoNaoPermitida;
import com.github.Hugoftf.Spring.JPA.service.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService){
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<Object> salvarLivro(@RequestBody @Valid CadastroLivroDTO livroDTO){

        try{

            return ResponseEntity.ok(livroDTO);

        } catch (OperacaoNaoPermitida e) {
            ErroResposta erroResposta = ErroResposta.respostaPadrao(e.getMessage());
            return ResponseEntity.status(erroResposta.status()).body(erroResposta);
        }
    }

}
