package com.example.biblioteca.controller;

import com.example.biblioteca.entity.Reserva;
import com.example.biblioteca.repository.ReservaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping ("/api/reserva")
public class ReservaController {
    @Autowired
    private ReservaRepository reservaRepository;

    @GetMapping (path = "/lista")
    public ResponseEntity<List<Reserva>> listarReservas () {
        List<Reserva> reserva = reservaRepository.findAll();
        return ResponseEntity.ok(reserva);
    }

    @GetMapping (path = "/{id}")
    public ResponseEntity<?> buscarReserva (@PathVariable Long id) {
        Optional<Reserva> reserva = reservaRepository.findById(id);
        if (reserva.isPresent()) {
            return ResponseEntity.ok(reserva.get());
        } else {
            return ResponseEntity.status(404).body("Reserva não encontrada");
        }
    }

    @PostMapping ("/criar")
    public ResponseEntity<?> criarReserva (@RequestBody Reserva reserva) {
        Reserva reservaSalvar = reservaRepository.save(reserva);
        return new ResponseEntity<>(reservaSalvar, HttpStatus.CREATED);
    }

    @DeleteMapping ("delete/{id}")
    public ResponseEntity<?> deletarReserva (@PathVariable Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return ResponseEntity.ok().body("Reserva deletada com sucesso");
        } else
            return ResponseEntity.status(404).body("Id da reserva não encontrado");
    }
}
