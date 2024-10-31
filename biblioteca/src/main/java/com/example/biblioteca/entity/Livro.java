package com.example.biblioteca.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Table (name = "tb_livros")
@Entity
@AllArgsConstructor
@NoArgsConstructor

public class Livro {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;

    @ManyToOne
    @JoinColumn (name = "autor", nullable = false)
    private Autor autor;

    private String editora;
    private String isbn;
    private LocalDate dataPublicacao;

    @ManyToOne
    @JoinColumn (name = "categoria_id", nullable = false)
    private Categoria categoria;

    private Long numeroexemplares;

}
