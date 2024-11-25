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
/**
 * Path de dominios da sua aplicação sempre no plural, exemplo: /api/usuarios, /api/livros, /api/categorias
 * Versionamento de endpoints: Sempre bom ter noção que seus endpoint podem sofrer versionamento
 * e alguns sistemas que utilizam ele não irão ser alterados, então é necessário criar uma v2 daquele endpoint.
 * Isso normalmente é feito via código, utilizando a nomenclatura de v1, v2 no path, exemplo: /api/v1/categorias
 */
@RequestMapping("/api/categoria")
public class CategoriaController {

    //Controllers não devem ter conhecimento ou acessar recursos dos repositories.
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Ao ter um controller de dominio, normalmente é utilizado apenas um get na rota definada no RequestMapping,
     * ficaria apenas @GetMapping()
     */
    @GetMapping (path = "/lista")
    public ResponseEntity<List<Categoria>> listaCategoria(){
        /**
         * Não tem necessidade de armazenar em um variaveis se não irá modificar o que vem nela, ou o método não ser muito extenso.
         * Exemplo:
         *
         * return ResponseEntity.ok(categoriaRepository.findAll());
         */
        List<Categoria> categorias = categoriaRepository.findAll();
        return ResponseEntity.ok(categorias);
    }

    @GetMapping (path = "/{id}")
    /**
     * Método sempre como verbos
     * busca -> buscar
     * salva -> salvar
     */
    public ResponseEntity<?> buscaCategoria(@PathVariable Long id){

        //belo uso do optional
        Optional<Categoria> categoria = categoriaRepository.findById(id);
        if (categoria.isPresent()) {
            return ResponseEntity.ok(categoria.get());
        } else {
            /**
             * normalmente erros 400, 404 e etc, são tratados via ExceptionHandler
             *
             * se existir -> retorna objeto
             * se não existir -> throw new RecursoNaoEncontradoException
             *
             * Isso faz com que todas exceptions disparadas pela sua aplicação tenham o mesmo tratamento, facilitando o uso posterior dela
             */
            return ResponseEntity.status(404).body("Categoria não encontrada");
        }
    }

    /**
     * Não há necessidade de nomear o path do Post, na mesma logica do get que expliquei lá em cima,
     * entende-se que ao ter um post no RequestMapping do dominio do controller entende-se que criará o objeto Categoria
     */
    @PostMapping ("/criar")
    public ResponseEntity<?> criaCategoria(@RequestBody Categoria categoria){
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return new ResponseEntity<>(categoriaSalva, HttpStatus.CREATED);
    }

    /**
     * Mesma coisa que o Get e Post, não tem necessidade de dizer que vai ser um delete se já esta definindo o endpoint com @Delete
     */
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<?> deletarCategoria(@PathVariable Long id){
        if (categoriaRepository.existsById(id)) {
            categoriaRepository.deleteById(id);
            return ResponseEntity.ok().body("Categoria deletada com sucesso");
        } else {
            // tratamento de exception
            return ResponseEntity.status(404).body("Id da categoria não encontrada");
        }
    }
}
