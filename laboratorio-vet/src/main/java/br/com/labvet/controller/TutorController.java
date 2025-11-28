package br.com.labvet.controller;

import br.com.labvet.dto.CadastroTutorRequest;
import br.com.labvet.model.Tutor;
import br.com.labvet.service.TutorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tutores")
public class TutorController {

    private final TutorService tutorService;

    public TutorController(TutorService tutorService) {
        this.tutorService = tutorService;
    }

    // 1. Cadastrar Tutor
    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody CadastroTutorRequest req) {
        // Converte o DTO para o Modelo
        Tutor novoTutor = new Tutor(
            req.getNome(), req.getSobrenome(), req.getDataNascimento(),
            req.getCpf(), req.getTelefone(), req.getEmail(),
            req.getEndereco(), req.getCep(), req.getGeneroTutor()
        );

        int idGerado = tutorService.cadastrarTutor(novoTutor);

        if (idGerado != -1) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Tutor cadastrado com ID: " + idGerado);
        } else {
            return ResponseEntity.badRequest().body("Erro ao cadastrar tutor (CPF duplicado?).");
        }
    }

    // 2. Buscar Tutor por CPF
    @GetMapping("/{cpf}")
    public ResponseEntity<?> buscarPorCpf(@PathVariable String cpf) {
        Tutor tutor = tutorService.buscarTutorPorCpf(cpf);
        if (tutor != null) {
            return ResponseEntity.ok(tutor);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}