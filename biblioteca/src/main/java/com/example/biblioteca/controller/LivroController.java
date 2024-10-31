package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Livro;
import com.example.biblioteca.repository.LivroRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/livros")
public class LivroController {
    @Autowired
    private LivroRepository livroRepository;

    @GetMapping(path = "/lista")
    public ResponseEntity<List<Livro>> findAll() {
        List<Livro> livros = livroRepository.findAll();
        return ResponseEntity.ok(livros);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> pegarLivroId(@PathVariable("id") Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);

        if (livroOptional.isPresent()) {
            return ResponseEntity.ok(livroOptional.get());
        } else {
            return ResponseEntity
                    .status(404)
                    .body("Livro não encontrado");
        }
    }

    @PostMapping(path = "/criar")
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }



    @DeleteMapping( "/delete/{id}")
    public ResponseEntity<?> deletarLivroId(@PathVariable("id") Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return ResponseEntity.ok("Livro deletado com sucesso");
        } else {
            return ResponseEntity
                    .status(404)
                    .body("Id do livro não cadastrado");
        }
    }
}