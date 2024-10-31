package com.example.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table (name = "tb_usuario")
public class Usuario {
    @Id
    @GeneratedValue ( strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String email;

    @OneToMany (mappedBy = "usuario")
    private List<Reserva> reservas;

}
