package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Autor;
import com.example.biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/autor")
public class AutorController {
    @Autowired
    private AutorRepository autorRepository;

    @GetMapping (path = "/lista")
    public ResponseEntity<List<Autor>> listaAutor() {
        List<Autor> autores = autorRepository.findAll();
        return ResponseEntity.ok(autores);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<?> buscaAutor(@PathVariable Long id) {
        Optional<Autor> autor = autorRepository.findById(id);
        if (autor.isPresent()) {
            return ResponseEntity.ok(autor.get());
        } else {
            return ResponseEntity.status(404).body("Autor não encontrado");
        }
    }

    @PostMapping ("/criar")
    public ResponseEntity<?> criaAutor(@RequestBody Autor autor) {
        Autor autorSalvar = autorRepository.save(autor);
        return new ResponseEntity<>(autorSalvar, HttpStatus.CREATED);
    }

   @DeleteMapping (path = "/delete/{id}")
    public ResponseEntity<?> deletaAutor(@PathVariable Long id) {
        if (autorRepository.existsById(id)) {
            autorRepository.deleteById(id);
            return ResponseEntity.ok().body("Autor deletado com sucesso");
        } else {
            return ResponseEntity.status(404).body("Id não encontrado");
        }
   }
}
