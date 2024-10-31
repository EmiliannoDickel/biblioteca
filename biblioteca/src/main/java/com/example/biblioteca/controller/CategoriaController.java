package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Categoria;
import com.example.biblioteca.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping (path = "/lista")
    public ResponseEntity<List<Categoria>> listaCategoria(){
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<?> buscaCategoria(@PathVariable Long id){
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            return ResponseEntity.status(404).body("Categoria não encontrada");
        }
    }

    @PostMapping ("/criar")
    public ResponseEntity<?> criaCategoria(@RequestBody Categoria categoria){
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id){
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().body("Categoria deletada com sucesso");
        } else {
            return ResponseEntity.status(404).body("Id da categoria não encontrada");
        }
    }
}
