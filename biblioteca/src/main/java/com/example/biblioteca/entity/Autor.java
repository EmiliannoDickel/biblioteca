package com.example.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "tb_autor")
public class Autor {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String nome;

    @OneToMany
    private List<Livro> livros;
}
