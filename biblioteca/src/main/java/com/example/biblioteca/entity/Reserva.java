package com.example.biblioteca.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Table (name = "tb_reserva")
@Entity
public class Reserva {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn ( name = "livro", nullable = false)
    private Livro livro;
    @ManyToOne
    @JoinColumn (name = "usuario", nullable = false)
    private Usuario usuario;

    private LocalDate dataReserva;
    private LocalDate dataDevolucao;
    private double multa;
    private int quantidade; // de livros

}
