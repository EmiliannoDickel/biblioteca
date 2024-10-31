package com.example.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "tb_categoria")

public class Categoria {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String descricao;
    @OneToMany (mappedBy = "categoria")
    private List<Livro> livros;
}
